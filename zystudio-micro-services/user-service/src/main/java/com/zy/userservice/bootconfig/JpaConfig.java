package com.zy.userservice.bootconfig;

import com.zy.ddd.support.spring.jpa.JpaBaseRepositoryFactoryBean;
import com.zy.ddd.support.spring.jpa.JpaBaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by zhougb on 2016/8/17.
 */
@Configuration
@EnableJpaRepositories(
        repositoryBaseClass = JpaBaseRepositoryImpl.class,
        //repositoryFactoryBeanClass = JpaBaseRepositoryFactoryBean.class,
        basePackages={"com.zy.userservice"})
public class JpaConfig {
}