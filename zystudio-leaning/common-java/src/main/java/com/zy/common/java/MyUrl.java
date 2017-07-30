package com.zy.common.java;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Administrator on 2017/6/4.
 */
public class MyUrl {

    public static void main(String argv[]){
        String str = "http://regcen-1:8802/eureka";
        try {
            URL url = new URL(str);
            URI uri = URI.create(str);
            System.out.println(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
