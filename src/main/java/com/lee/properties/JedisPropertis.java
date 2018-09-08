package com.lee.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 加载以jedis开头的属性
 */
@Component
@ConfigurationProperties(prefix = "jedis")
public class JedisPropertis {

    private String uri;
    private int commandTimeOut;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getCommandTimeOut() {
        return commandTimeOut;
    }

    public void setCommandTimeOut(int commandTimeOut) {
        this.commandTimeOut = commandTimeOut;
    }
}
