package com.chukun.datastruct.sort;

import java.util.Arrays;

/**
 * @author chukun
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
       int[] array = new int[]{8,2,13,-1,79};
       bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 冒泡排序
     * @param array
     */
    public static void bubbleSort(int [] array){
        boolean isSorted = false;
        for(int i=0;i<array.length-1;i++){
            for(int j=0;j<array.length-1-i;j++){
                if (array[j]>array[j+1]) {
                    isSorted = true;
                    int temp = array[j+1];
                    array[j+1] =array[j];
                    array[j] = temp;
                }
                //如果不进行交换，说明数组已经有序
                if(!isSorted){
                    break;
                }
            }
        }
    }
}
