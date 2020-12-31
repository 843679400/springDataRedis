package com.example.demo;

import com.example.demo.framework.utils.RedisUtil;
import com.sun.istack.internal.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class DemoApplicationTests {

    Logger log = Logger.getLogger(DemoApplicationTests.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisConnection redisConnection;

    @Test
    void contextLoads() {
        log.info(redisTemplate.getClientList().get(0).toString());
    }

    @Test
    void set(){
        //redisTemplate.opsForValue().setBit("a",2,false);
        //redisUtil.setStringValue("k1","1");
        log.info(redisConnection.bitCount("a".getBytes())+"");
    }

}
