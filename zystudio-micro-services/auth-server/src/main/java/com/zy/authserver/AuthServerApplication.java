package com.zy.authserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author by zy.
 */
@SpringBootApplication
public class AuthServerApplication {
    public static void main(String argv[]){
        new SpringApplicationBuilder(AuthServerApplication.class).web(true).run();
    }
}
