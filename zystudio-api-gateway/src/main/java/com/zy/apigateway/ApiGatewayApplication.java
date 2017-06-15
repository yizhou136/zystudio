package com.zy.apigateway;

import com.zy.apigateway.filter.AccessFilter;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


/**
 * @author by zy.
 */
@SpringCloudApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class ApiGatewayApplication {

    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }

    public static void main(String argv[]){
        new SpringApplicationBuilder(ApiGatewayApplication.class).web(true).run();
    }
}
