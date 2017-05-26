package com.zy.jersey.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author by zy.
 */
@SpringBootApplication
public class JerseyApplication {

    public static void main(String argv[]){
        new SpringApplicationBuilder(JerseyApplication.class).web(true).run();
    }
}