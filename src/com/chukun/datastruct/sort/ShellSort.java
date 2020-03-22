package com.chukun.datastruct.sort;

import java.util.Arrays;

/**
 * @author chukun
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
       int[] array = new int[]{8,9,1,7,2,3,4,6,0};
//       shellSort(array);
       movePositionShellSort(array);
       System.out.println(Arrays.toString(array));
    }

    /**
     * 希尔排序 交换法实现
     * @param array
     */
    public static void shellSort(int[] array){
        int temp = 0;
        for(int gap=array.length/2;gap>0;gap/=2){
            for(int i=gap;i<array.length;i++){
                //遍历各组中的所有的元素，共gap组，步长gap
                for(int j=i-gap;j>=0;j-=gap){
                    //如果当前元素大于加上步长之后的元素，就交换
                    if(array[j]>array[j+gap]){
                        temp = array[j+gap];
                        array[j+gap]=array[j];
                        array[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序 移位法实现
     * @param array
     */
    public static void movePositionShellSort(int[] array){
        for(int gap = array.length/2;gap>0;gap/=2){
            for(int i=gap;i<array.length;i++){
                int j = i;
                int temp = array[j];
                if(array[j]<array[j-gap]){
                    while (j-gap>=0 && temp<array[j-gap]){
                        array[j] = array[j-gap];
                        j-=gap;
                    }
                    //找到插入的位置
                    array[j] = temp;
                }
            }
        }
    }
}
