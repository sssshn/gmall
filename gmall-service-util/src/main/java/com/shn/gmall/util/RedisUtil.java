package com.shn.gmall.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    private JedisPool jedisPool;

    public void initPool(String host, int port, int database) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //可用连接实例的最大数目，默认值为8。如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted（耗尽）
        jedisPoolConfig.setMaxTotal(200);
        //控制一个pool最多有多少个状态为idle（空闲的）jedis实例，默认值也是8
        jedisPoolConfig.setMaxIdle(30);
        //连接耗尽时是否阻塞，false报异常，true阻塞直到超时，默认为true
        jedisPoolConfig.setBlockWhenExhausted(true);
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1 表示永不超时
        jedisPoolConfig.setMaxWaitMillis(10*1000);
        //在borrow一个jedis实例时，是否提前进行validate操作，在获取连接的时候检查有效性；如果为true，则得到的jedis实例均是可用的 默认为false
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(jedisPoolConfig, host, port, 20*1000);
    }

    public Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
}
