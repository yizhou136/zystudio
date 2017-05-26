package com.zy.ddd.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Optional;


/**
 * @author by zy.
 */
public abstract class AbstractDomainObject implements Serializable{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private static final long serialVersionUID = 1L;


    protected <R> Optional<R> publishEvent(DomainEvent domainEvent){
        return DefaultDomainEventPublisher
                .getInstance()
                .publishEvent(domainEvent);
    }
}