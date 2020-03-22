package com.chukun.datastruct.search;

/**
  *@auther : chukun
  *@description : 插值查找算法
  *@create : 2019/7/3 23:29
  *@copyright www.hualala.com
  */
public class InsertValueSearch {


    public static void main(String[] args) {
        int [] array = new int[100];
        for(int i=0;i<array.length;i++){
            array[i] = i;
        }
        int i = insertValueSearch(array, 0, array.length - 1, 99);
        System.out.println(i);
    }

    /**
     *插值查找算法，比较适合，一些分布比较均匀的有序数据的查找
     * @param array
     * @param left
     * @param right
     * @param findValue
     * @return
     */
    public static int insertValueSearch(int[] array,int left,int right,int findValue){
        //验证数据的输入的正确性
        if(left>right || findValue<array[0] || findValue>array[array.length-1]){
            return -1;
        }
        //插值查找算法的公式
        // int mid  = left + (key-array[left])/(array[right]-array[left]) *(right-left)
        int mid  = left + (findValue-array[left])/(array[right]-array[left]) *(right-left);
        int midValue = array[mid];
        if(findValue>midValue){
            return insertValueSearch(array,mid+1,right,findValue);
        }else if(findValue<array[mid]){
            return insertValueSearch(array,left,mid-1,findValue);
        }else{
            return mid;
        }
    }
}
