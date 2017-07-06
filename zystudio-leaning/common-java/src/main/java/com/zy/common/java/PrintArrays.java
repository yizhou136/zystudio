package com.zy.common.java;

/**
 * Created by Administrator on 2017/6/21.
 */
public class PrintArrays {

    public static void printArr(int arrs[][]){
        int top = 0;
        int bottom = arrs.length-1;
        int left = 0;
        int right = arrs[0].length-1;

        while (left < right || top < bottom){
            for(int i=top; i<=right; i++){
              System.out.println(arrs[left][i]);
            }
            top++;

            for (int i=top; i<=bottom; i++){
                System.out.println(arrs[i][right]);
            }
            right--;

            for (int i=right; i>=left; i--){
                System.out.println(arrs[bottom][i]);
            }
            bottom--;

            for (int i=bottom; i>top; i--){
                System.out.println(arrs[i][left]);
            }
            left++;
        }
    }

    public static void main(String argv[]){
        int [][] arrs = new int[][]{
                {1,  2,  3,  4,  5},
                {14, 15, 16, 17, 6},
                {13, 20, 19, 18, 7},
                {12, 11, 10, 9,  8},
        };
        printArr(arrs);
    }
}
