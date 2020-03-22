package com.chukun.algorithm.kmp;

import java.util.Arrays;

public class StringKMPMatch {

    public static void main(String[] args) {

        String dest="ABBAAC";
        int[] next = kmpNext(dest);
        System.out.println("next="+ Arrays.toString(next));
    }


    /**
     *
     * @param source
     * @param dest
     * @param next
     * @return
     */
    public static int kmpSearch(String source,String dest,int[] next){
         for(int i=0,j=0;i<source.length();i++){
             //需要判断source.charAt(i)!=dest.charAt(j)的情况，利用next数组调整
             while (j>0 && source.charAt(i)!=dest.charAt(j)){
                 j = next[j-1];
             }
             if(source.charAt(i)==dest.charAt(j)){
                 j++;
             }
             if(j==dest.length()){
                 return i-j+1;
             }
         }
         return -1;
    }
    /**
     * 计算kmp的部分匹配数组
     * 例如:
     *  ABBA 的前缀 [A,AB,ABB] 后缀[BBA,BA,A] 所以匹配个数1个
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest){

        int [] next = new int[dest.length()];
        //一个字符串的部分匹配为0
        next[0] = 0;
        for(int i=1,j=0;i<dest.length();i++){
            while (j>0 && dest.charAt(i)!=dest.charAt(j)){
                j = next[j-1];
            }
            if(dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
