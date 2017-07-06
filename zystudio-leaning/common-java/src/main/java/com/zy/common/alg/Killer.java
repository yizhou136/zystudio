package com.zy.common.alg;

import com.zy.common.alg.tree.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * @author by zy.
 */
public class Killer {
    private static Node<Integer> root = null;

    private static void setup(){
        root = new Node(0);
        Node<Integer> cur = root;
        for (int i=1; i<40; i++){
            Node<Integer> node = new Node(i);
            cur.setNext(node);
            cur = node;
        }
    }

    private static void println(){
        Node cur = root;
        while (cur != null){
            System.out.println(cur.getVal());
            cur = cur.getNext();
        }
    }

    private static void kill(List<Integer> s,  int cnt){

    }

    public static final void main(String argv[]){
        setup();
        println();
    }
}
