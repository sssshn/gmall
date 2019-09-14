package com.shn.gmall.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.shn.gmall.bean.CartInfo;
import com.shn.gmall.cart.mapper.CartInfoMapper;
import com.shn.gmall.service.CartService;
import com.shn.gmall.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartInfoMapper cartInfoMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void syncCartListCookieToDb(List<CartInfo> cartInfoList, String userId) {
        for (CartInfo cartInfo : cartInfoList) {
            CartInfo info = new CartInfo();
            info.setUserId(userId);
            info.setSkuId(cartInfo.getSkuId());
            CartInfo selectOne = cartInfoMapper.selectOne(info);
            if (selectOne == null) {
                cartInfo.setUserId(userId);
                cartInfoMapper.insertSelective(cartInfo);
            } else {
                selectOne.setSkuNum(selectOne.getSkuNum() + cartInfo.getSkuNum());
                selectOne.setCartPrice(selectOne.getSkuPrice().multiply(new BigDecimal(selectOne.getSkuNum())));
            }
        }

        syncCache(userId);
    }

    @Override
    public void changeCartSelect(CartInfo cartInfo) {

        Example example = new Example(CartInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", cartInfo.getUserId());
        criteria.andEqualTo("skuId", cartInfo.getSkuId());
        cartInfoMapper.updateByExampleSelective(cartInfo, example);

        //同步缓存
        syncCache(cartInfo.getUserId());
    }

    @Override
    public List<CartInfo> getCartInfoListFromCache(String userId) {
        Jedis jedis = redisUtil.getJedis();
        List<String> list = jedis.hvals("carts:" + userId + ":info");
        List<CartInfo> cartInfoList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (String s : list) {
                CartInfo cartInfo = JSON.parseObject(s, CartInfo.class);
                cartInfoList.add(cartInfo);
            }
        }
        return cartInfoList;
    }

    @Override
    public void syncCache(String userId) {
        CartInfo cartInfo = new CartInfo();
        List<CartInfo> cartInfoList = cartInfoMapper.select(cartInfo);
        Map<String, String> map = new HashMap<>();
        Jedis jedis = redisUtil.getJedis();
        for (CartInfo info : cartInfoList) {
            map.put(info.getId(), JSON.toJSONString(info));
        }

        jedis.hmset("carts:" + userId + ":info", map);

    }

    @Override
    public void addCartInfo(CartInfo cartInfo) {
        cartInfoMapper.insertSelective(cartInfo);
    }

    @Override
    public void updateCartInfo(CartInfo cartInfo) {
        CartInfo cartInfo1 = new CartInfo();
        cartInfo1.setId(cartInfo.getId());
        cartInfo1.setCartPrice(cartInfo.getCartPrice());
        cartInfo1.setSkuNum(cartInfo.getSkuNum());
        cartInfoMapper.updateByPrimaryKeySelective(cartInfo1);
    }

    @Override
    public CartInfo getCartInfoListBySkuId(String skuId, String userId) {

        CartInfo cartInfo = new CartInfo();
        cartInfo.setUserId(userId);
        cartInfo.setSkuId(skuId);
        return cartInfoMapper.selectOne(cartInfo);
    }
}
