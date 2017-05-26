package com.zy.registry.test.bootconfig;

import com.zy.registry.test.service.UserService;
import com.zy.registry.test.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author by zy.
 */
@Configuration
public class TimeoutConfiguration {
    protected final Logger logger = LoggerFactory.getLogger(TimeoutConfiguration.class);

    @Bean
    public UserService userService(){
        logger.info("start build UserService {}",System.currentTimeMillis());
        UserService userService = new UserServiceImpl();
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("finished build UserService {}",System.currentTimeMillis());
        return userService;
    }
}
