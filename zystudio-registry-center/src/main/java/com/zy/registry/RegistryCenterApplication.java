package com.zy.registry;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author by zy.
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryCenterApplication {

    public static void main(String argv[]){
        new SpringApplicationBuilder(RegistryCenterApplication.class).web(true).run();
    }
}