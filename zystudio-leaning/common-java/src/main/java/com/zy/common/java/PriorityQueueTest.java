package com.zy.common.java;

import java.util.PriorityQueue;

/**
 * @author by zy.
 */
public class PriorityQueueTest {

    public static final  void main(String argv[]){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1);

        priorityQueue.add(6);
        priorityQueue.add(5);
        priorityQueue.add(2);
        priorityQueue.add(2);
        priorityQueue.add(4);

        priorityQueue.forEach(System.out::print);
        priorityQueue.remove();
        priorityQueue.remove();
        priorityQueue.remove();
        System.out.println();
        priorityQueue.forEach(System.out::print);
    }
}
