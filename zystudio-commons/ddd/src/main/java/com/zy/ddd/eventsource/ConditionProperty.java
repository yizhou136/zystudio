package com.zy.ddd.eventsource;

/**
 * @author by zy.
 */
public class ConditionProperty extends BaseProperty{
    public ConditionProperty(Object propValue, String propName) {
        super(propValue, propName);
    }

    public ConditionProperty(Object propValue, int idx) {
        super(propValue, idx);
    }

    public ConditionProperty(Object propValue, int idx, String propName) {
        super(propValue, idx, propName);
    }
}
