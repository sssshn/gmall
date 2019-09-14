package com.shn.gmall.passport.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shn.gmall.bean.CartInfo;
import com.shn.gmall.bean.UserInfo;
import com.shn.gmall.service.CartService;
import com.shn.gmall.service.UserService;
import com.shn.gmall.util.CookieUtil;
import com.shn.gmall.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sss
 */
@Controller
public class PassportController {

    @Reference
    private UserService userService;
    @Reference
    private CartService cartService;

    @RequestMapping("/verify")
    @ResponseBody
    public String verify(String token, String salt) {

        Map userMap = JwtUtil.decode("gmall", token, salt);
        if (userMap != null) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        UserInfo user = userService.getUserByNameAndPassWord(userInfo);
        if (user == null) {
            return "用户名或密码错误!";
        }
        //将用户信息保存到redis缓存
        userService.saveUserInfoCache(user);

        //同步购物车
        String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
        List<CartInfo> cartInfoList = JSON.parseArray(cartListCookie, CartInfo.class);
        if (cartInfoList != null && cartInfoList.size() > 0) {
            cartService.syncCartListCookieToDb(cartInfoList, user.getId());
            CookieUtil.deleteCookie(request, response, "cartListCookie");
        }

        //获取ip地址
        String ipAddress = request.getRemoteAddr();
        if (StringUtils.isBlank(ipAddress)) {
            ipAddress = request.getHeader("x-forwarded-for");
        }

        Map<String, String> map = new HashMap<>();
        map.put("nickName", user.getNickName());
        map.put("userId", user.getId());
        String token = JwtUtil.encode("gmall", map, ipAddress);

        return token;
    }

    /**
     * 跳转 登录页面，并将登陆之前的请求路径保存起来
     * @return
     */
    @RequestMapping("/index")
    public String index(String returnURL, Map map) {
        map.put("returnURL", returnURL);
        return "index";
    }
}
