package com.zy.ddd.domain.api;

import com.zy.ddd.domain.DomainEvent;

/**
 * @author by zy.
 */
public class DummyDomainEvent extends DomainEvent {

    public DummyDomainEvent(Object source) {
        super(source);
    }
}
