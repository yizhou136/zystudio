package com.zy.common.alg.tree;

import java.util.List;

/**
 * @author by zy.
 */
public class MultiNode {
    private String name;
    private List<MultiNode> children;

    public MultiNode(String name, List<MultiNode> children) {
        this.name = name;
        this.children = children;
    }

    public MultiNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MultiNode> getChildren() {
        return children;
    }

    public void setChildren(List<MultiNode> children) {
        this.children = children;
    }
}
