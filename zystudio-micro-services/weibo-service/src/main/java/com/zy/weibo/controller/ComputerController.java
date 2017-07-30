package com.zy.weibo.controller;

import com.google.common.base.Strings;
import com.zy.weibo.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Stream;

/**
 * @author by zy.
 */
@RestController
public class ComputerController {
    private static final Logger logger = LoggerFactory.getLogger(ComputerController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    private int idx;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public int add(Integer a, Integer b, HttpServletRequest request){
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        Integer res = a + b;
        Cookie cookies[] = request.getCookies();
        idx++;
        logger.info("serviceInstance host:{} serviceId:{} add:{} cookies:{} idx:{}",
                serviceInstance.getHost(), serviceInstance.getServiceId(), res, cookies, idx);
        if (cookies != null)
            Stream.of(cookies).forEach((c)->{
                logger.info("cookie {} name:{} value:{}",
                        c, c.getName(), c.getValue());
            });

        if ((idx&1) == 1){
            throw new RuntimeException("My Exception");
        }
        return res;
    }

    @RequestMapping(value = "/regUser",
            method = {RequestMethod.GET, RequestMethod.POST})
    public User regUser(@RequestBody User user){
        logger.info("regUser user:{}",user);
        user.setUid(user.getUid()+1);
        return user;
    }


    @RequestMapping(value = "/regUser2",
            method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public User regUser2(@Valid User user, BindingResult bindingResult){
        logger.info("regUser2 user:{} bindingResult:{}",user, bindingResult);
        user.setUid(user.getUid()+1);
        return user;
    }
}