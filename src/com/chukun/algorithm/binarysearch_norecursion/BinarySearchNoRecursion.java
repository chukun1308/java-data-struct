package com.chukun.algorithm.binarysearch_norecursion;

/**
  *@auther : chukun
  *@description : 二分查找非递归版本
  *@create : 2019/7/26 16:27
  *@copyright www.hualala.com
  */
public class BinarySearchNoRecursion {

    public static void main(String[] args) {

        int[] array={5,9,12,18,28,36,45};
        int search = binarySearch(array, 28);
        System.out.println(search);
    }

    /**
     * 二分查找，非递归版本
     * @param array
     * @param value
     * @return
     */
    public static int binarySearch(int[] array,int value){
       int left = 0;
       int right = array.length-1;
       while(left<right){
           int mid = (right+left)/2;
           if(array[mid]==value){
               return mid;
           }else if(array[mid]>value){
               right = mid;
           }else {
               left = mid;
           }
       }
       return -1;
    }
}

