package com.zy.userservice.domain.cmdmodel.identity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;

import javax.persistence.*;

/**
 * @author by zy.
 */
//@Configurable
public class UserEntityListener {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    //@Autowired
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PrePersist
    public void  prePersist(Object object){
        logger.info("prePersist xxxxxxx obj:{} appcontext:{}",
                object, applicationContext);
    }

    @PostPersist
    public void postPersist(Object object){
        logger.info("postPersist xxxxxxxobj:{}", object);
    }

    @PostRemove
    public void postRemove(Object object){
        logger.info("postRemove xxxxxxxobj:{}", object);
    }

    @PreRemove
    public void preRemove(Object object){
        logger.info("preRemove xxxxxxxobj:{}", object);
    }

    @PostUpdate
    public void postUpdate(Object object){
        logger.info("postUpdate xxxxxxxobj:{}", object);
    }

    @PostLoad
    public void postLoad(Object object){
        logger.info("postLoad xxxxxxxobj:{} applicationContext:{}",
                object, applicationContext);
    }

    @PreUpdate
    public void preUpdate(Object object){
        logger.info("preUpdate xxxxxxxobj:{}", object);
    }
}
