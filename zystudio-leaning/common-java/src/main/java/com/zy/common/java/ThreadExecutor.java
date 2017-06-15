package com.zy.common.java;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by zy.
 */
public class ThreadExecutor {
    private static final ThreadLocal<Integer> local = new ThreadLocal<>();
    private static final InheritableThreadLocal<Integer> inhLocal = new InheritableThreadLocal<>();

    public static void testThreadLocal(){
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

    public static void testSynchnousBlockingQueue() throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>(false);
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("start take ");
                    String r = synchronousQueue.take();
                    System.out.println("taked r:"+r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(100);
        System.out.println("start offer.");
        boolean res = synchronousQueue.offer("a");
        System.out.println("end offer. res:"+res+" "+synchronousQueue.size());
    }

    public static void main(String[] args) throws InterruptedException {
        testSynchnousBlockingQueue();
    }
}