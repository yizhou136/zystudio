package com.zy.ddd.eventsource;

/**
 * @author by zy.
 */
public class ValueProperty extends BaseProperty{

    public ValueProperty(Object propValue, String propName) {
        super(propValue, propName);
    }

    public ValueProperty(Object propValue, int idx) {
        super(propValue, idx);
    }

    public ValueProperty(Object propValue, int idx, String propName) {
        super(propValue, idx, propName);
    }
}