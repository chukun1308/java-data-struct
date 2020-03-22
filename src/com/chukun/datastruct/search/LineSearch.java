package com.chukun.datastruct.search;

/**
  *@auther : chukun
  *@description : 线性查找
  *@create : 2019/7/2 0:16
  *@copyright www.hualala.com
  */
public class LineSearch {

    public static void main(String[] args) {
        int[] array = new int[]{8,2,13,-1,79};
        int i = lineSearch(array, 2);
    }

    /**
     *
     * @param array
     * @param findValue
     * @return
     */
    public static int lineSearch(int [] array,int findValue){
        for (int i = 0; i < array.length; i++) {
            if(array[i]==findValue){
                return i;
            }
        }
        return -1;
    }
}
