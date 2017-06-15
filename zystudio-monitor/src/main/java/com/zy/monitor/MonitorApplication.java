package com.zy.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author by zy.
 */
@SpringBootApplication
@EnableAdminServer
public class MonitorApplication {

    public static void main(String argv[]){
        new SpringApplicationBuilder(MonitorApplication.class).web(true).run();
    }
}