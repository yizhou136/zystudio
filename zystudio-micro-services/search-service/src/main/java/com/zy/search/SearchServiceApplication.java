package com.zy.search;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author by zy.
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class SearchServiceApplication {

    public static void main(String argv[]){
        new SpringApplicationBuilder(SearchServiceApplication.class).web(false).run();
    }
}