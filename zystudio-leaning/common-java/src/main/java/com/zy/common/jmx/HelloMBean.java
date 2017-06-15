package com.zy.common.jmx;

/**
 * @author by zy.
 */
public interface HelloMBean {
    String getName();
    void setName(String name);
    String getAge();
    void setAge(String age);
    void helloWorld();
    void helloWorld(String str);
    void getTelephone();
}
