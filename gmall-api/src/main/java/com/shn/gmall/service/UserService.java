package com.shn.gmall.service;

import com.shn.gmall.bean.UserInfo;

public interface UserService {

    void saveUserInfoCache(UserInfo userInfo);

    UserInfo getUserByNameAndPassWord(UserInfo userInfo);
}
