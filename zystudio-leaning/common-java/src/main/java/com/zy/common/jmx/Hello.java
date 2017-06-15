package com.zy.common.jmx;

/**
 * @author by zy.
 */
public class Hello implements HelloMBean{
    private String name;
    private String age;

    @Override
    public String getName() {
        System.out.println("getName");
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println("setName name:"+name);
        this.name = name;
    }

    @Override
    public String getAge() {
        System.out.println("getAge");
        return age;
    }

    @Override
    public void setAge(String age) {
        System.out.println("setAge age:"+age);
        this.age = age;
    }

    @Override
    public void helloWorld() {
        System.out.println("helloWorld");
    }

    @Override
    public void helloWorld(String str) {
        System.out.println("helloWorld str:"+str);
    }

    @Override
    public void getTelephone() {
        System.out.println("getTelephone");
    }
}
