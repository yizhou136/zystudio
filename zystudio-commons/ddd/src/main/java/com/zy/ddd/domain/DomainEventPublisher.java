package com.zy.ddd.domain;

import java.util.Optional;

/**
 * @author by zy.
 */
public interface DomainEventPublisher {

    <R> Optional<R> publishEvent(DomainEvent domainEvent);

    void registerEventListener(DomainEventListener domainEventListener);
}
