package com.shn.gmall.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shn.gmall.bean.CartInfo;
import com.shn.gmall.bean.SkuInfo;
import com.shn.gmall.service.CartService;
import com.shn.gmall.service.SkuService;
import com.shn.gmall.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Reference
    private SkuService skuService;
    @Reference
    private CartService cartService;

    /**
     * 异步购物车选中
     * @return
     */
    @RequestMapping("/checkCart")
    public String checkCart(HttpServletRequest request, HttpServletResponse response, CartInfo cartInfo, ModelMap map) {
        List<CartInfo> cartInfoList = null;
        String userId = "1";
        if (StringUtils.isBlank(userId)) {
            //用户未登录修改cookie
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
            if (StringUtils.isNotBlank(cartListCookie)) {
                cartInfoList = JSON.parseArray(cartListCookie, CartInfo.class);
                for (CartInfo info : cartInfoList) {
                    if (info.getSkuId().equals(cartInfo.getSkuId())) {
                        info.setIsChecked(cartInfo.getIsChecked());
                    }
                }
            }
            CookieUtil.setCookie(request, response, "cartListCookie", JSON.toJSONString(cartInfoList), 60*60*24*7, true);

        } else {
            //用户已登录,修改数据库,同步缓存
            cartInfo.setUserId(userId);
            cartService.changeCartSelect(cartInfo);
            cartInfoList = cartService.getCartInfoListFromCache(userId);
        }

        map.addAttribute("cartList", cartInfoList);
        return "cartListInner";
    }

    /**
     * 跳转购物车列表
     * @return
     */
    @RequestMapping("/cartList")
    public String cartList(HttpServletRequest request, ModelMap modelMap) {
        String userId = "1";
        List<CartInfo> cartInfoList = null;
        if (StringUtils.isBlank(userId)) {
            //用户未登录，从cookie去取
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
            if (StringUtils.isNotBlank(cartListCookie)) {
                cartInfoList = JSON.parseArray(cartListCookie, CartInfo.class);
            }
        } else {
            //用户已登录,从缓存中取
            cartInfoList = cartService.getCartInfoListFromCache(userId);
        }

        //总金额
        BigDecimal bigDecimal = new BigDecimal("0");
        if (cartInfoList != null && cartInfoList.size() > 0) {
            for (CartInfo cartInfo : cartInfoList) {
                bigDecimal = bigDecimal.add(cartInfo.getCartPrice());
            }
        }

        modelMap.addAttribute("totalPrice", bigDecimal);
        modelMap.addAttribute("cartList", cartInfoList);
        return "cartList";
    }


    /**
     * 添加购物车功能
     * @param request
     * @param response
     * @param cartInfo
     * @return
     */
    @RequestMapping("/addToCart")
    public String addToCart(HttpServletRequest request, HttpServletResponse response, CartInfo cartInfo) {

        List<CartInfo> cartInfoList = new ArrayList<>();
        String skuId = cartInfo.getSkuId();
        SkuInfo skuInfo = skuService.getSkuInfoById(skuId);
        cartInfo.setSkuName(skuInfo.getSkuName());
        cartInfo.setImgUrl(skuInfo.getSkuDefaultImg());
        cartInfo.setIsChecked("1");
        cartInfo.setSkuPrice(new BigDecimal(skuInfo.getPrice()));
        cartInfo.setCartPrice(new BigDecimal(skuInfo.getPrice()).multiply(new BigDecimal(cartInfo.getSkuNum())));

        String userId = "1";
        //用户未登录
        if (StringUtils.isBlank(userId)) {
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
            //cookie不存在
            if (StringUtils.isBlank(cartListCookie)) {
                cartInfoList.add(cartInfo);
            } else {
                //cookie存在
                cartInfoList = JSON.parseArray(cartListCookie, CartInfo.class);
                //当前sku是否已经存在
                boolean flag = true;
                for (CartInfo info : cartInfoList) {
                    if (info.getSkuId().equals(skuId)) {
                        //存在
                        flag = false;
                        info.setSkuNum(info.getSkuNum() + cartInfo.getSkuNum());
                        info.setCartPrice(info.getSkuPrice().multiply(new BigDecimal(info.getSkuNum())));
                    }
                }
                //不存在
                if (flag) {
                    cartInfoList.add(cartInfo);
                }
            }
            CookieUtil.setCookie(request, response, "cartListCookie", JSON.toJSONString(cartInfoList), 60*60*24*7, true);

            //用户登录了
        } else {

            cartInfo.setUserId(userId);
            CartInfo cartInfoDb = cartService.getCartInfoListBySkuId(skuId, userId);
            if (cartInfoDb == null) {
                //不存在
                cartService.addCartInfo(cartInfo);
            } else {
                cartInfoDb.setSkuNum(cartInfoDb.getSkuNum() + cartInfo.getSkuNum());
                cartInfoDb.setCartPrice(cartInfoDb.getSkuPrice().multiply(new BigDecimal(cartInfoDb.getSkuNum())));
                cartService.updateCartInfo(cartInfoDb);
            }
            //同步缓存
            cartService.syncCache(userId);

        }

        return "redirect:/cartSuccess";
    }

    /**
     * 跳转购物车添加成功页面
     * @return
     */
    @RequestMapping("/cartSuccess")
    public String cartSuccess() {
        return "success";
    }
}
