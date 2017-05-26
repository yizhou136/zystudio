package com.zy.ddd.domain;

import java.util.EventObject;

/**
 * @author by zy.
 */
public class DomainEvent extends EventObject{

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DomainEvent(Object source) {
        super(source);
    }


}
