package com.zy.userservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author by zy.
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
public class UserServiceApplication {

    public static void main(String argv[]){
        new SpringApplicationBuilder(UserServiceApplication.class).web(true).run();
    }
}