package com.chukun.datastruct.search;

import java.util.ArrayList;
import java.util.List;

/**
  *@auther : chukun
  *@description : 二分查找
  *@create : 2019/7/2 0:19
  *@copyright www.hualala.com
  */
public class BinarySearch {

    public static void main(String[] args) {
        int[] array = new int[]{8,9,13,21,21,21,79,88,90};
        int i = binarySearchFindOne(array, 0, array.length - 1, 21);
        System.out.println(i);
        List<Integer> integers = binarySearchFindAll(array, 0, array.length - 1, 21);
        System.out.println(integers);
    }

    /**
     *查询等于findValue的下标
     * @param array
     * @param left
     * @param right
     * @param findValue
     * @return
     */
    public static int binarySearchFindOne(int [] array,int left,int right,int findValue){
        if(left>right){
            return -1;
        }
        int mid = (left+right)/2;
        if (findValue>array[mid]) {
            return binarySearchFindOne(array,mid+1,right,findValue);
        }else if(findValue<array[mid]){
            return binarySearchFindOne(array,left,mid-1,findValue);
        }else {
            return mid;
        }
    }

    /**
     * 查询所有等于findValue的下标
     * @param array
     * @param left
     * @param right
     * @param findValue
     * @return
     */
    public static List<Integer> binarySearchFindAll(int[] array,int left,int right,int findValue){
        if(left>right){
            return new ArrayList<>();
        }
        int mid = (left+right)/2;
        if (findValue>array[mid]) {
            return binarySearchFindAll(array,mid+1,right,findValue);
        }else if(findValue<array[mid]){
            return binarySearchFindAll(array,left,mid-1,findValue);
        }else {
            //向左，向右遍历，找到全部等于findValue的下标
            int tempIndex = mid-1;
            List<Integer> indexList = new ArrayList<>();
            indexList.add(mid);
            while(true){
                //向左遍历
                if(tempIndex<0 || array[tempIndex]!=findValue){
                    break;
                }
                indexList.add(tempIndex);
                tempIndex--;

                //向右遍历
                tempIndex = mid+1;
                if(tempIndex>array.length-1 || array[tempIndex]!=findValue){
                    break;
                }
                indexList.add(tempIndex);
                tempIndex++;
            }
            return indexList;
        }
    }
}
