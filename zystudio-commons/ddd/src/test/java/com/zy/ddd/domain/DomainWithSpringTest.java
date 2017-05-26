package com.zy.ddd.domain;

import com.zy.ddd.AbstractSpringBootTest;
import com.zy.ddd.domain.api.DummyDomainEvent;
import org.junit.Test;

import java.util.Optional;

/**
 * @author by zy.
 */
public class DomainWithSpringTest extends AbstractSpringBootTest{

    @Test
    public void testEventPublisherWithSpring(){
        logger.info("testEventPublisherWithSpring goto publishEvent");
        Optional<String> result = DefaultDomainEventPublisher
                .getInstance()
                .publishEvent(new DummyDomainEvent("hahah"));
        logger.info("testEventPublisherWithSpring end publishEvent re:{}",
                result);
        java.util.PriorityQueue a;
        result.ifPresent(logger::info);
    }
}