package com.zy.jvm.classloader;

import com.zy.jvm.classloader.order.Greeter;
import com.zy.jvm.classloader.order.Message;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/8/27.
 */
public class access {

    public static void main(String argv[]){
        Message message = new Message("msg");
        try {

            Field [] fields= Message.class.getFields();
            System.out.println(fields.length);
            fields= Message.class.getDeclaredFields();
            System.out.println(fields.length);
            Field intField = Message.class.getField("age");
            intField = Message.class.getDeclaredField("age");
            Field field = Message.class.getDeclaredField("m_text");
            field.setAccessible(true);
            String msg = (String)field.get(message);
            System.out.println(msg);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
