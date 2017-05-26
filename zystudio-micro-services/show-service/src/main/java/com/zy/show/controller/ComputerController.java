package com.zy.show.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by zy.
 */
@RestController
public class ComputerController {
    private static final Logger logger = LoggerFactory.getLogger(ComputerController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/sub", method = RequestMethod.GET)
    public int sub(Integer a, Integer b){
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        Integer res = a - b;
        logger.info("serviceInstance host:{} serviceId:{} add:{} ",
                serviceInstance.getHost(), serviceInstance.getServiceId(), res);

        return res;
    }
}
