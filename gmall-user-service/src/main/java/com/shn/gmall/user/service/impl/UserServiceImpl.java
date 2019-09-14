package com.shn.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.shn.gmall.bean.UserInfo;
import com.shn.gmall.service.UserService;
import com.shn.gmall.user.mapper.UserInfoMapper;
import com.shn.gmall.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public void saveUserInfoCache(UserInfo userInfo) {
        Jedis jedis = redisUtil.getJedis();
        jedis.set("user:" + userInfo.getId() + ":info", JSON.toJSONString(userInfo));
        jedis.close();
    }

    @Override
    public UserInfo getUserByNameAndPassWord(UserInfo userInfo) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginName" , userInfo.getLoginName());
        criteria.andEqualTo("passwd", userInfo.getPasswd());
        return userInfoMapper.selectOneByExample(example);
    }
}
