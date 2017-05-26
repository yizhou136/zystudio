package com.zy.weibo.test.hystrix;

import com.netflix.hystrix.*;
import com.netflix.hystrix.contrib.javanica.command.HystrixCommandFactory;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author by zy.
 */
public class HystrixHelloWorld extends HystrixCommand<String>{
    private static final Logger logger = LoggerFactory.getLogger(HystrixHelloWorld.class);
    private String name;

    private static final Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HystrixHelloWorldGroup"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("helloworld"))
            .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("helloworld_threadpool"))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter());

    public HystrixHelloWorld(String name){
        super(setter);
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        if (true)
            throw new HystrixBadRequestException("run error");
        //throw new RuntimeException("run error");
        logger.info("run {}",name);
        return name;
    }

    @Override
    protected String getFallback() {
        return "fallbacked "+name;
    }

    @Override
    protected String getCacheKey() {
        return "oneCache";
    }
}