package com.chukun.datastruct.sort;

import java.util.Arrays;

/**
  *@auther : chukun
  *@description : 快速排序
  *@create : 2019/6/30 1:01
  *@copyright www.hualala.com
  */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = new int[]{8,2,13,-1,79,23,0};
        quickSort(array,0,array.length-1);
        System.out.println("array="+ Arrays.toString(array));
    }

    /**
     * kuaisu排序算法
     * @param array
     * @param left
     * @param right
     */
    public static void quickSort(int[] array,int left,int right){
        //获取中间值为基准值
        int middle = (left+right)/2;
        int pivot = array[middle];
        int l =left;
        int r = right;
        while(l<r){
            while (array[l]<pivot){
                l++;
            }
            while (array[r]>pivot) {
                r--;
            }
            int temp = array[r];
            array[r] = array[l];
            array[l] = temp;
            if(l>=r){
                break;
            }
            //如果交换完成，发现array[r] ==pivot ,就让l++，后移
            if(array[r]==pivot){
                l++;
            }
            //如果交换完成，发现array[l] ==pivot ,就让r--,前移
            if(array[l]==pivot){
                r--;
            }
        }
        //如果l==r,就让l++, r--, 否则会出现栈溢出
        if(l==r){
            l++;
            r--;
        }
        //向左递归
        if(left<r){
            quickSort(array,left,r);
        }
        if(right>l){
            //向右递归
            quickSort(array,l,right);
        }
    }
}
