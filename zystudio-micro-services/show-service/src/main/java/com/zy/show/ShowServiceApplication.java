package com.zy.show;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author by zy.
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
public class ShowServiceApplication {

    public static void main(String argv[]){
        new SpringApplicationBuilder(ShowServiceApplication.class).web(true).run();
    }
}