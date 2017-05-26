package com.zy.weibo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.zy.weibo.beans.User;
import com.zy.weibo.bootconfig.mvc.KryoHttpMessageConverter;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author by zy.
 */
@FeignClient(value = "weibo-service", fallback = WeiboServiceHystrixFallback.class)
public interface WeiboServiceClient {

    @HystrixCommand(commandProperties =
            {
                    //@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY,value = "SEMAPHORE"),
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS,value = "1000")})
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    Integer  add(@RequestParam("a")Integer a, @RequestParam("b") Integer b);



    @RequestMapping(value = "/regUser",
            method = RequestMethod.POST, consumes = "application/x-kryo")
    @HystrixCommand(commandProperties =
            {
                    //@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY,value = "SEMAPHORE"),
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS,value = "5000")})
    User regUser(User user);
}