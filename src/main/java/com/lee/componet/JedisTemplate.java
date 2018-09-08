package com.lee.componet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;

/**
 * 自定义redis工具类
 */
@Component
public class JedisTemplate {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * set方法
     * @param key
     * @param value
     * @param time  过期时间
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public <T> String setStr(String key,T value,Long time) throws JsonProcessingException {
        String o = bean2String(value);
        if(time==null){
            return jedisCluster.set(key,o);
        }
        return jedisCluster.set(key,o,"NX","EX",time);
    }

    /**
     * 通过key获取value
     * @param key
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> Object getStr(String key,Class<T> clazz) throws IOException {
        String s = jedisCluster.get(key);
        if(clazz==null){
            return s;
        }
        T t= string2Obj(s,clazz);
        return t;

    }
    //将字符串转换成对象
    private <T> T string2Obj(String str,Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //获取value的类型
        if(str==null||str.length()<0||clazz==null) {
            return null;
        }
        if(clazz==int.class||clazz==Integer.class) {
            return (T) Integer.valueOf(str);
        }else if(clazz==String.class) {
            return (T) str;
        }else if(clazz==long.class||clazz==Long.class) {
            return (T) Long.valueOf(str);
        }else {
            return (T)mapper.readValue(str,clazz);
        }
    }

    /**
     * 对象转字符串
     * @param value
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    private <T> String bean2String(T value) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if(value==null) {
            return null;
        }
        Class<?> clazz= value.getClass();
        if(clazz==int.class||clazz==Integer.class) {
            return ""+value;
        }else if(clazz==String.class) {
            return (String)value;
        }else if(clazz==long.class||clazz==Long.class) {
            return ""+value;
        }else {
            return mapper.writeValueAsString(value);
        }
    }
}
