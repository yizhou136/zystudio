package com.zy.jvm.classloader.order;

/**
 * Created by Administrator on 2017/8/26.
 */
public class Message extends Greeter{

    private String m_text;

    public Message(String text) {
        m_text = text;
    }

    public void print(java.io.PrintStream ps) {
        ps.println(m_text);
    }
}
