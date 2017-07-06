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
        cur.setNext(root);
    }

    private static void println(){
        Node cur = root;
        while (cur != null){
            System.out.println(cur.getVal());
            cur = cur.getNext();
            if(cur == root)
                break;
        }
    }

    private static Node kill(Node root, int cnt){
        Node prev = null;
        Node cur = root;

        int i = 1;
        while (cur != null){
            prev = cur;
            cur = cur.getNext();
            if (cur !=null && cur.getNext() == root)
                break;
            i++;
        }
        i++;
        if (i < cnt) return root;

        i = 0;
        while (i < cnt){
            if (cur.getNext().getNext() == prev) {
                break;
            }
            prev = prev.getNext();
            cur = cur.getNext();
            i++;

            if (i == cnt){
                prev.setNext(cur.getNext());
                cur.setNext(null);
                cur = prev.getNext();
                i = 0;
            }
        }

        //kill(prev.getNext());
        System.out.println("cur:"+cur.getVal()+" prev:"+prev.getVal());
        return cur;
    }


    public static final void main(String argv[]){
        setup();
        println();
        root = kill(root, 10);

        println();
    }
}
