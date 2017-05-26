package com.zy.ddd.eventsource;

/**
 * @author by zy.
 */
public abstract class BaseProperty {
    protected int idx;
    protected String propName;
    protected Object propValue;

    public BaseProperty(Object propValue, String propName){
        this(propValue, -1, propName);
    }

    public BaseProperty(Object propValue, int idx){
        this(propValue, idx, null);
    }

    public BaseProperty(Object propValue, int idx, String propName){
        this.idx = idx;
        this.propName = propName;
        this.propValue = propValue;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Object getPropValue() {
        return propValue;
    }

    public void setPropValue(Object propValue) {
        this.propValue = propValue;
    }
}
