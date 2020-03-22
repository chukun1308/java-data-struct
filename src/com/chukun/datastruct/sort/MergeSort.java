package com.chukun.datastruct.sort;

import java.util.Arrays;

/**
  *@auther : chukun
  *@description : 归并排序
  *@create : 2019/6/30 21:57
  *@copyright www.hualala.com
  */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{8,2,13,-1,79,23,0};
        int[]temp = new int[array.length];
        mergeSort(array,0,array.length-1,temp);
        System.out.println("array="+ Arrays.toString(temp));
    }

    public static void mergeSort(int[] array,int left,int right,int[] temp){

        if(left<right){
            int mid = (left+right)/2;
            //向左递归
            mergeSort(array,left,mid,temp);
            //向右递归
            mergeSort(array,mid+1,right,temp);
            //合并
            merge(array,left,mid,right,temp);
        }
    }

    /**
     * 归并排序,合并
     * @param array  待合并的数组
     * @param left   左下标
     * @param mid    中间下标
     * @param right  右边下标
     * @param temp   临时数组
     */
    public static void merge(int[] array,int left,int mid,int right,int[] temp){
        int i = left;
        int j = mid+1;
        //指向当前临时数组的下标
        int t = 0;
        while(i<=mid && j<=right){
            if(array[i]<array[j]){
                temp[t] = array[i];
                t++;
                i++;
            }else{
                temp[t] = array[j];
                j++;
                t++;
            }
        }
        //退出循环后，检查下，左边坐标的值是否到达mid
        while (i<=mid){
            temp[t] = array[i];
            i++;
            t++;
        }
        //判断右边的坐标是否到达right
        while (j<=right){
            temp[t] = array[j];
            j++;
            t++;
        }

        //将temp的数据copy到原数组
        t=0;
        int tempLeft = left;
        System.out.println("tempLeft="+tempLeft+" right="+right);
        while (tempLeft<=right){
            array[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
