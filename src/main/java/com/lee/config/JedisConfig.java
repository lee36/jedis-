package com.lee.config;

import com.lee.properties.JedisPropertis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * jedis的配置类
 */
@Configuration
@EnableConfigurationProperties(JedisPropertis.class)
public class JedisConfig {

    @Autowired
    private JedisPropertis jedisPropertis;

    @Bean
    public JedisCluster jedisCluster(){
        String[] uris = jedisPropertis.getUri().split(",");
        Set<HostAndPort> sets=new HashSet<HostAndPort>();
        for (String uri : uris) {
            String[] split = uri.split(":");
            sets.add(new HostAndPort(split[0].trim(),Integer.valueOf(split[1].trim())));
        }
        JedisCluster cluster = new JedisCluster(sets,jedisPropertis.getCommandTimeOut());
        return cluster;
    }
}
