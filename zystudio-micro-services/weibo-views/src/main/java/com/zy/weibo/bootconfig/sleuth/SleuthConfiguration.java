package com.zy.weibo.bootconfig.sleuth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by zy.
 */
@Configuration
public class SleuthConfiguration {

    @Bean
    public AlwaysSampler alwaysSampler(){
        return new AlwaysSampler();
    }
}
