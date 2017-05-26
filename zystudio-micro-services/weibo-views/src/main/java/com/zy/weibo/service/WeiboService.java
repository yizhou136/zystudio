package com.zy.weibo.service;

import com.google.common.base.Joiner;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.zy.weibo.controller.WeiboServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author by zy.
 */
@Service
public class WeiboService {
    private static final Logger logger = LoggerFactory.getLogger(WeiboService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeiboServiceClient weiboServiceClient;

    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String addServiceByRestTemplate(Integer a, Integer b){
        return restTemplate.getForEntity(
                String.format("http://weibo-service/add?a=%d&b=%d", a, b),
                String.class).getBody();
    }

    @HystrixCommand(fallbackMethod = "addServiceFallback"
            /*commandProperties ={@HystrixProperty(name = "hystrix.command.default.metrics.rollingStats.numBuckets", value = "10"),
             @HystrixProperty(name = "hystrix.command.default.metrics.rollingStats.timeInMilliseconds", value = "122"),}*/
             )
    //@HystrixCollapser()
    public String addServiceByFeign(Integer a, Integer b){
        logger.info("addServiceByFeign a:{}, b:{}", a, b);
        Integer integer = weiboServiceClient.add(a, b);
        return String.valueOf(integer);
    }

    public String addServiceFallback(Integer a, Integer b){
        return "return by HystrixCommand fallback a:"+a+" b:"+b;
    }
}