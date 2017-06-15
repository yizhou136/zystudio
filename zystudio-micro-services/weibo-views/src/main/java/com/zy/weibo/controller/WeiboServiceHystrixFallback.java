package com.zy.weibo.controller;

import com.zy.weibo.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author by zy.
 */

@Component
public class WeiboServiceHystrixFallback implements WeiboServiceClient{
    private static final Logger logger = LoggerFactory.getLogger(WeiboServiceHystrixFallback.class);

    @Override
    public Integer add(Integer a, Integer b, String accessToken) {
        logger.info("by WeiboServiceHystrixFallback a:{}  b:{} accessToken:{}",
                a, b, accessToken);

        return -1;
    }

    @Override
    public User regUser(User user) {
        logger.info("by WeiboServiceHystrixFallback user:{}",
                 user);
        return null;
    }

    @Override
    public void mywebsocket() {
        logger.info("by WeiboServiceHystrixFallback mywebsocket");
    }
}
