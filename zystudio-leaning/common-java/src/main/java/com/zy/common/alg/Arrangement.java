package com.zy.common.alg;

import java.util.stream.Stream;

/**
 * @author by zy.
 */
public class Arrangement {
    private static char ARR[] = {'a', 'b', 'c', 'd', 'e'};

    public static void printFullArrangement(char arr[], int k){

    }



    public static void printArrangement(char arr[], int start,  int len, StringBuffer stringBuffer){
        if (len == 1){
            //stringBuffer.append(arr[start+len-1]);
            System.out.print(stringBuffer+""+arr[start+len-1]+""+arr[start+len-2]);
            System.out.println();
            System.out.print(stringBuffer+""+arr[start+len-2]+""+arr[start+len-1]);
            System.out.println();
            //stringBuffer.deleteCharAt(stringBuffer.length()-1);
            /*System.out.print(arr[start+len-1]);
            System.out.println();
            System.out.print(arr[start+len-1]);
            System.out.print(arr[start+len-2]);
            System.out.println();*/
        }else {
            for (int i=start; i <(start+len-2); i++){
                stringBuffer.append(ARR[i]);
                printArrangement(arr, start+1, len - 1, stringBuffer);
            }
        }
    }

    private static void swap(char arr[], int f, int t){
        arr[f] ^= arr[t];
        arr[t] = (char) (arr[f] ^ arr[t]);
        arr[f] = (char) (arr[f] ^ arr[t]);
    }

    private static void printAll(){
        Stream.of(ARR).forEach(System.out::print);
    }


    private  static void printArrangement2(char arr[], int start,  int len){
        if (len == 1){
            printAll();
        }else {
            for (int i=start; i<(start+len); i++){
                swap(arr, 1,i);
                printArrangement2(arr, start+1, len-1);
                swap(arr, i, 1);
            }
        }
    }

    private static void perm(char num[], int start,int end){
        int i;
        if(start > end){
            for(i = 0 ;i<=end;i++){
                System.out.print(ARR[i]+" ");
            }
            System.out.println();
        }else{
            System.out.println("start:"+start+" end:"+end);
            for(i = start; i<= end; i++){
                System.out.println("swap1:"+start+" i:"+i);
                swap(start,i);
                perm(ARR,start+1, end);
                swap(start,i);
            }
        }
    }

    private static void swap(int n1,int n2){
        char tem = ARR[n1];
        ARR[n1] = ARR[n2];
        ARR[n2] = tem;
    }

    public static void main(String argv[]){
        //printArrangement(ARR, 0, 4, new StringBuffer());
        perm(ARR, 0, 3);

        /*System.out.println("swap");
        swap(ARR, 0, 1);
        printAll();*/
    }
}
