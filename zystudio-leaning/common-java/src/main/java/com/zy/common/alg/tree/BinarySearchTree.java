package com.zy.common.alg.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author by zy.
 */
public class BinarySearchTree {
    private Node<Integer> root;

    public Node<Integer> search(int k){
        Node<Integer> cur = root;

        while (cur != null && cur.getVal() != k) {
            if (k < cur.getVal()){
                cur = cur.getLeft();
            }else {
                cur = cur.getRight();
            }
        }

        return cur;
    }

    public void insert(int v){
        Node<Integer> newNode = new Node<Integer>(v);
        if (root == null) {
            root = newNode;
            return;
        }

        Node<Integer> cur = root;
        Node<Integer> prev = null;
        while (cur.getVal() != v) {
            prev = cur;
            if (v < cur.getVal()){
                cur = cur.getLeft();
                if (cur == null){
                    prev.setLeft(newNode);
                    return;
                }
            }else {
                cur = cur.getRight();
                if (cur == null){
                    prev.setRight(newNode);
                    return;
                }
            }
        }
        cur.setNext(newNode);
        //prev.setLeft(newNode);
        //cur = new Node<Integer>(v);
    }

    public void preprint(Node<Integer> cur){
        if (cur == null)
            return;

        System.out.print(cur.getVal());
        System.out.print(",");
        preprint(cur.getLeft());
        preprint(cur.getRight());
    }

    public void midprint(Node<Integer> cur){
        if (cur == null)
            return;
        midprint(cur.getLeft());
        System.out.print(cur.getVal());
        System.out.print(",");
        Node<Integer> next = cur;
        /*while ((next = next.getNext()) != null){
            System.out.print(next.getVal());
            System.out.print(",");
        }*/
        midprint(cur.getRight());
    }

    public void afterprint(Node<Integer> cur){
        if (cur == null)
            return;
        afterprint(cur.getLeft());
        afterprint(cur.getRight());
        System.out.print(cur.getVal());
        System.out.print(",");
    }

    public void wideprint(){
        System.out.println("start wideprint");
        ArrayDeque<Node<Integer>> queue = new ArrayDeque<>();
        queue.add(root);

        boolean startLeft = true;
        while (!queue.isEmpty()){
            Node<Integer> node = queue.remove();
            System.out.print(node.getVal());
            System.out.print(",");
            if (startLeft){
                if (node.getRight() != null)
                    queue.add(node.getRight());
                if (node.getLeft() != null)
                    queue.add(node.getLeft());
            }else {
                if (node.getRight() != null)
                    queue.add(node.getRight());
                if (node.getLeft() != null)
                    queue.add(node.getLeft());
            }
        }
        System.out.println();
        System.out.println("end wideprint");
    }

    public void sawtoothPrint(){

    }

    public static void main(String argv[]){
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(5);
        binarySearchTree.insert(2);
        binarySearchTree.insert(3);
        binarySearchTree.insert(8);
        binarySearchTree.insert(1);
        binarySearchTree.insert(7);
        binarySearchTree.insert(9);

        binarySearchTree.insert(3);
        binarySearchTree.insert(2);


        System.out.println(binarySearchTree);

        binarySearchTree.preprint(binarySearchTree.root);
        System.out.println();
        binarySearchTree.midprint(binarySearchTree.root);
        System.out.println();
        binarySearchTree.afterprint(binarySearchTree.root);
        System.out.println();

        binarySearchTree.wideprint();
    }
}
