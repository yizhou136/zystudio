package com.zy.common.java;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by zy.
 */
public class ThreadExecutor {
    private static final ThreadLocal<Integer> local = new ThreadLocal<>();
    private static final InheritableThreadLocal<Integer> inhLocal = new InheritableThreadLocal<>();
    public static void main(String[] args) {

        local.set(1);
        inhLocal.set(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer integer = local.get();
                System.out.println("currentThread:"+Thread.currentThread().getName()+" int:"+integer);

                integer = inhLocal.get();
                System.out.println("currentThread:"+Thread.currentThread().getName()+" inhLocal int:"+integer);
            }
        }).start();
    }
}
