package com.lee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lee.componet.JedisTemplate;
import com.lee.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Test {
    @Autowired
    private JedisTemplate jedisTemplate;
    @RequestMapping("/")
    public Object test() throws IOException {
        jedisTemplate.getStr("haha",User.class);
    }
    @RequestMapping("/test/{value}")
    public String add(@PathVariable("value") String value) throws JsonProcessingException {
        String aa = jedisTemplate.setStr("haha",new User(1,"123"),null);
        return aa;
    }
}
