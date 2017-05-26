package com.zy.ddd.domain;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EventListener;
import java.util.Objects;

/**
 * @author by zy.
 */
public interface DomainEventListener<E extends DomainEvent> extends EventListener, Comparable<DomainEventListener>{

    <R> EventResult<R> handleEvent(E e, EventResult prevResult);

    default int getOrder(){return 0;}

    default int compareTo(DomainEventListener o){
        Objects.requireNonNull(o,
                "the other DomainEventListener is null");
        return Integer.compare(this.getOrder(), o.getOrder());
    }

    default Class[] receiveActualType(){
        Class result[] = null;
        Type[] types = getClass().getGenericInterfaces();
        for (Type type: types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                result = new Class[1];
                result[0] = (Class) (parameterizedType.getActualTypeArguments()[0]);
            }
        }
        return result;
    }
}