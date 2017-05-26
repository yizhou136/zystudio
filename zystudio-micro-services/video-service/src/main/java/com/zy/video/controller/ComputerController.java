package com.zy.weibo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

/**
 * @author by zy.
 */
@RestController
public class ComputerController {
    private static final Logger logger = LoggerFactory.getLogger(ComputerController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public int add(Integer a, Integer b, HttpServletRequest request){
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        Integer res = a + b;
        Cookie cookies[] = request.getCookies();
        logger.info("serviceInstance host:{} serviceId:{} add:{} cookies:{}",
                serviceInstance.getHost(), serviceInstance.getServiceId(), res, cookies);
        if (cookies != null)
            Stream.of(cookies).forEach((c)->{
                logger.info("cookie {} name:{} value:{}",
                        c, c.getName(), c.getValue());
            });

        return res;
    }
}
