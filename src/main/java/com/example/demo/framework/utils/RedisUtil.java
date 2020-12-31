package com.example.demo.framework.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setStringValue(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public String getStringValue(String key){
        return  (String)redisTemplate.opsForValue().get(key);
    }
}
