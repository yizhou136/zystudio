package com.zy.ddd.domain.api;

import com.zy.ddd.domain.DomainEventListener;
import com.zy.ddd.domain.EventResult;

/**
 * @author by zy.
 */
public class DummyDomainEventListener implements DomainEventListener<DummyDomainEvent> {
    private String name;
    private int order;
    public DummyDomainEventListener(String name, int order){
        this.name = name;
        this.order = order;
    }

    @Override
    public EventResult<String> handleEvent(DummyDomainEvent dummyDomainEvent, EventResult eventResult) {
        System.out.println("onDomainEvent dummyDomainEvent:"
                +dummyDomainEvent+" "+toString());

        return EventResult.continueResult(toString());
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return String.format("name:%s order:%d", name, order);
    }
}