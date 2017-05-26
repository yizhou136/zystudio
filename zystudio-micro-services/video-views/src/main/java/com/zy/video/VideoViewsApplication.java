package com.zy.weibo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author by zy.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
//@EnableAspectJAutoProxy
@EnableHystrixDashboard
//@EnableTurbine
public class VideoViewsApplication {


    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String argv[]){
        int n = -2;
        int len = 8;
        int i = (n - 1) % len;
        i = (n-1+len) % len;
        int bi = (n-1) & (len-1);
        System.out.println(i+" "+bi);

        i = -2;
        System.out.println(i>>1);
        System.out.println(i>>>1);
        new SpringApplicationBuilder(VideoViewsApplication.class).web(true).run();
    }
}