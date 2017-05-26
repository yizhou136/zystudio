package com.zy.ddd.support.spring.jpa;


import com.zy.ddd.domain.EventResult;

/**
 * @author by zy.
 */
public interface EventRepository<CMD_DomainEvent>{

    EventResult handle(CMD_DomainEvent domainEvent);

}
