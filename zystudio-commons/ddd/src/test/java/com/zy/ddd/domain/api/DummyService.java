package com.zy.ddd.domain.api;

import com.zy.ddd.domain.EventResult;
import com.zy.ddd.support.spring.annotation.DomainMethodEventListener;
import com.zy.ddd.support.spring.annotation.DomainEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * @author by zy.
 */
@DomainEventListener
public class DummyService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public void doSomething1(){
        logger.info("doSomething1");
    }


    @DomainMethodEventListener
    @Order(2)
    public EventResult dummyMethodEventListener1(DummyDomainEvent dummyDomainEvent){
        doSomething1();
        logger.info("dummyMethodEventListener1 dummyDomainEvent:{}", dummyDomainEvent);
        return EventResult.continueResult(getClass().toString());
    }

    @DomainMethodEventListener
    @Order(1)
    public EventResult dummyMethodEventListener2(DummyDomainEvent dummyDomainEvent){
        doSomething1();
        logger.info("dummyMethodEventListener2 dummyDomainEvent:{}", dummyDomainEvent);
        return EventResult.continueResult(getClass().toString());
    }
}
