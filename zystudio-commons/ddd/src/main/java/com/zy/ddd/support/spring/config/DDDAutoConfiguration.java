package com.zy.ddd.support.spring.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author by zy.
 */
@Configuration
@ConditionalOnProperty(value = "ddd.enabled", matchIfMissing = true)
public class DDDAutoConfiguration {

    /*@Bean
    public DomainEventPublisher defaultDomainEventPublisher(){
        return new DefaultDomainEventPublisher();
    }*/


    @Bean
    public DomainEventListenerMethodProcessor domainEventListenerMethodProcessor(){
        return new DomainEventListenerMethodProcessor();
    }

    /*public static class DDDImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        }
    }*/
}