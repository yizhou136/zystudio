package com.zy.ddd.domain;

import java.util.*;

/**
 * @author by zy.
 */
public class DefaultDomainEventPublisher implements DomainEventPublisher{

    private static final ThreadLocal<DomainEventPublisher> instance = new ThreadLocal<DomainEventPublisher>() {
        protected DomainEventPublisher initialValue() {
            return new DefaultDomainEventPublisher();
        }
    };
    public static DomainEventPublisher getInstance() {
        return instance.get();
    }

    private Map<Class, Set<DomainEventListener>> listenerMap;

    public DefaultDomainEventPublisher(){
        listenerMap = new HashMap<>(10);
    }

    public void registerEventListener(DomainEventListener domainEventListener){
        Class[] actualTypes = domainEventListener.receiveActualType();
        Objects.requireNonNull(actualTypes, ()->{return String.format("the %s`s receiveActualType is null",
                domainEventListener);});

        Set<DomainEventListener> listenerSet = null;
        for (Class actualType : actualTypes) {
            listenerSet = listenerMap.putIfAbsent(actualType, listenerSet);
            if (listenerSet == null){
                //listenerSet = new HashSet<>(5);
                listenerSet = Collections.newSetFromMap(new TreeMap<DomainEventListener, Boolean>());
                listenerMap.put(actualType, listenerSet);
            }
            listenerSet.add(domainEventListener);
        }


    }

    /*@Override
    public Optional<R> publishEvent(DomainEvent domainEvent) {
        Set<DomainEventListener> listenerSet = listenerMap.get(domainEvent.getClass());
        if (listenerSet == null)
            return;

        listenerSet.forEach((l)->{
            if ()
        });
    }*/

    @Override
    public <R> Optional<R> publishEvent(DomainEvent domainEvent) {
        Optional<R> optional = Optional.empty();
        Set<DomainEventListener> listenerSet = listenerMap.get(domainEvent.getClass());
        if (listenerSet == null)
            return optional;

        EventResult prevEventResult = null;
        for (DomainEventListener listener : listenerSet){
            EventResult eventResult = listener.handleEvent(domainEvent, prevEventResult);
            optional = eventResult.getResult();
            if (!eventResult.isNeedContinue()){
                break;
            }
            prevEventResult = eventResult;
        }

        return optional;
    }
}
