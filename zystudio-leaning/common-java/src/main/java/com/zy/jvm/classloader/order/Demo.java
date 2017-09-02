package com.zy.jvm.classloader.order;

/**
 * Created by Administrator on 2017/8/26.
 */
public class Demo {

    public static void main(String[] args) {
        System.out.println("**beginning execution**");
        Greeter greeter = new Greeter();
        System.out.println("**created Greeter**");
        greeter.greet();
    }
}
