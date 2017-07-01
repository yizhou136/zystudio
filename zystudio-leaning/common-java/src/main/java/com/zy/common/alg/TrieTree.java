package com.zy.common.alg;

import java.util.HashMap;
import java.util.Set;

/**
 * @author by zy.
 */
public class TrieTree {

    private HashMap<String, Object>  senstivsWordTree;


    public void addSenstiveWord(Set<String> wordSet){
        senstivsWordTree = new HashMap<>(wordSet.size());


    }
}
