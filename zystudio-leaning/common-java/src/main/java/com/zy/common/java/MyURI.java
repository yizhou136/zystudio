package com.zy.common.java;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author by zy.
 */
public class MyURI {

    public static void main(String argv[]){
        String uri = "http://bjdx1-regcen:1100/eureka/";

        try {
            URI uri1 = new URI(uri);
            System.out.println(uri1.getHost());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
