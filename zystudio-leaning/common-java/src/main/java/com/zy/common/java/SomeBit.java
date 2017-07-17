package com.zy.common.java;

import java.util.ArrayList;

/**
 * @author by zy.
 */
public class SomeBit {
    private static final char[] chars =
            {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};


    public static final String printBit(int i, int bit){
        StringBuilder stringBuilder = new StringBuilder();
        while (i > 0){
            int mod = i % bit;
            i = i / bit;
            //System.out.print(chars[mod]);
            stringBuilder.append(chars[mod]);
        }
        return stringBuilder.reverse().toString();
    }

    public static final int oonverTo(int i, int bit){
        int res = 0;
        int level = 0;
        while (i > 0){
            int mod = i % bit;

            res = res + (level*bit) + mod;
            i = i / bit;

            level = i;
        }

        return res;
    }

    public static void main(String argv[]){
        int[] bits = new int[]{11, 12, 15, 16};
        for (int bit : bits) {
            int i1 = Integer.valueOf("84", bit);
            int i2 = Integer.valueOf("148", bit);
            System.out.println(i1 + " " + i2);
            int i = i1 * i2;
            System.out.println(printBit(i, bit));
            /*System.out.println(printBit(i, 12));
            System.out.println(printBit(i, 15));
            System.out.println(printBit(i, 16));*/
        }
    }
}
