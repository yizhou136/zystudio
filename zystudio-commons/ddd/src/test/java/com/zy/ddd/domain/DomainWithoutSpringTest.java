package com.zy.ddd.domain;

import com.zy.ddd.AbstractBaseTest;
import com.zy.ddd.domain.api.DummyDomainEvent;
import com.zy.ddd.domain.api.DummyDomainEventListener;
import org.junit.Test;

import java.util.Optional;

/**
 * @author by zy.
 */
public class DomainWithoutSpringTest extends AbstractBaseTest {

    @Test
    public void testEventPublisherWithoutSpring(){
        logger.info("testEventPublisherWithoutSpring goto publishEvent");
        DefaultDomainEventPublisher defaultDomainEventPublisher =
                new DefaultDomainEventPublisher();

        defaultDomainEventPublisher
                .registerEventListener(new DummyDomainEventListener("listener1",1));

        defaultDomainEventPublisher
                .registerEventListener(new DummyDomainEventListener("listener2",2));

        Optional<String> result = defaultDomainEventPublisher
                .publishEvent(new DummyDomainEvent("dummy"));
        result.ifPresent(logger::info);
    }
}