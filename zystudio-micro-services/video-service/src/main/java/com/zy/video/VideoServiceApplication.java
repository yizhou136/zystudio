package com.zy.video;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author by zy.
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
public class VideoServiceApplication {

    public static void main(String argv[]){
        new SpringApplicationBuilder(VideoServiceApplication.class).web(true).run();
    }
}