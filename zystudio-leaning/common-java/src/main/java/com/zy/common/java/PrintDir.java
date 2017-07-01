package com.zy.common.java;

import java.io.File;

/**
 * @author by zy.
 */
public class PrintDir {


    private static void printDir(String pre, String path){
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f:files){
            if (f.isDirectory()) {
                System.out.println(pre + f.getName());
                printDir(pre + "  ", f.getAbsolutePath());
            }else
                System.out.println(pre+f.getName());
        }
    }


    public static final void main(String argv[]){
        printDir("","D:\\work-note\\wangju");
    }
}
