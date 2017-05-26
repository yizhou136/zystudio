package com.zy.weibo.test.archaius;

import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;
import org.junit.Test;

/**
 * @author by zy.
 */
public class TestArchaius {

    @Test
    public void testArch() throws InterruptedException {
        DynamicLongProperty key1Property =
                DynamicPropertyFactory.getInstance().getLongProperty("key1", 1000);

        key1Property.addCallback(new Runnable() {
            @Override
            public void run() {
                System.out.println("key1Property properties file had changed");
                System.out.println("new key1Property:"+key1Property.get());
            }
        });

        System.out.println("key1Property:"+key1Property.get());

        synchronized (this) {
            wait();
        }
    }
}
