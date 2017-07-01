package com.zy.common.alg.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author by zy.
 */
public class TreeWideSearch {

    public static MultiNode makeMultiNodes(){
        MultiNode multiNodeZ = new MultiNode("z");

        MultiNode multiNodeY = new MultiNode("y");
        MultiNode multiNodeH = new MultiNode("h");
        MultiNode multiNodeG = new MultiNode("g");
        MultiNode multiNodeF = new MultiNode("f");

        MultiNode multiNodeE = new MultiNode("e", Arrays.asList(new MultiNode[]{multiNodeZ}));
        MultiNode multiNodeD = new MultiNode("d",
                Arrays.asList(new MultiNode[]{multiNodeY}));
        MultiNode multiNodeC = new MultiNode("c",
                Arrays.asList(new MultiNode[]{multiNodeG,multiNodeH}));
        MultiNode multiNodeB = new MultiNode("b",
                Arrays.asList(new MultiNode[]{multiNodeE,multiNodeF}));
        MultiNode multiNodeA = new MultiNode("a",
                Arrays.asList(new MultiNode[]{multiNodeB,multiNodeC,multiNodeD}));


        return multiNodeA;
    }

    public static void printWideSearch(MultiNode root){
        if (root == null)
            return;
        ArrayDeque<MultiNode> queue = new ArrayDeque<>();
        System.out.println(root.getName());
        root.getChildren().forEach((n)->{queue.add(n);});
        while (!queue.isEmpty()){
            MultiNode tmp = queue.removeFirst();
            System.out.println(tmp.getName());
            if (tmp.getChildren() != null){
                tmp.getChildren().forEach((n)->{queue.addLast(n);});
            }
        }
    }

    public static final void main(String argv []){
        printWideSearch(makeMultiNodes());
    }
}
