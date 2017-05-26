package com.zy.jersey.springboot.bootconfig;

import com.zy.jersey.springboot.resources.user.UserResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * @author by zy.
 */
@Configuration
public class JerseyConfig {

    @Bean
    public ResourceConfig resourceConfig(){
        Set<Class<?>> set = new HashSet<>();
        set.add(UserResource.class);
        /*Application application = new Application(){
            @Override
            public Set<Class<?>> getClasses() {
                Set<Class<?>> set = new HashSet<>();
                set.add(UserResource.class);
                return set;
            }
        };*/
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages("com.zy.jersey");
        return resourceConfig;
    }
}
