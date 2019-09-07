package com.shn.gmall.service;

import com.shn.gmall.bean.CartInfo;

import java.util.List;

public interface CartService {

    void changeCartSelect(CartInfo cartInfo);

    List<CartInfo> getCartInfoListFromCache(String userId);

    void syncCache(String userId);

    void addCartInfo(CartInfo cartInfo);

    void updateCartInfo(CartInfo cartInfo);

    CartInfo getCartInfoListBySkuId(String skuId, String userId);
}
