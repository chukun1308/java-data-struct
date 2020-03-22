package com.chukun.datastruct.sort;

import java.util.Arrays;

/**
 * @author chukun
 * 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = new int[]{8,2,13,-1,79};
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 选择排序
     * @param array
     */
    public static void selectSort(int [] array){
        for(int i=0;i<array.length-1;i++){
            int minIndex = i;
            int min = array[i];
            for(int j=i+1;j<array.length;j++){
                if (min>array[j]) {
                    minIndex = j;
                    min = array[j];
                }
            }
            //如果不进行交换，说明数组已经有序
            if(minIndex!=i){
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
    }
}
