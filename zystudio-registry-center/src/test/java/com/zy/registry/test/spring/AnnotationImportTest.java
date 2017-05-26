package com.zy.registry.test.spring;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author by zy.
 */
public class AnnotationImportTest implements ApplicationContextAware , InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(AnnotationImportTest.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info(" setApplicationContext applicationContext:{}", applicationContext);
    }
}