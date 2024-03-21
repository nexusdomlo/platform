package com.example.platform.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest//在测试类上添加这个注解，那么将来单元测试方法执行之前，会先初始化Spring容器
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testSet(){
        ValueOperations<String,String>operations = stringRedisTemplate.opsForValue();
        operations.set("username","hsh");
    }
    @Test
    public void testGet(){
        ValueOperations<String,String>operations = stringRedisTemplate.opsForValue();
        System.out.println(operations.get("username"));
    }
}
