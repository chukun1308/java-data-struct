package com.chukun.datastruct.sort;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] array = new int[]{8,2,13,-1,79};
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 插入排序
     * @param array
     */
    public static void insertSort(int[] array){
        for(int i=0;i<array.length;i++){
            int insertVal = array[i];
            int insertIndex = i-1;
            while(insertIndex>=0 && insertVal<array[insertIndex]){
                array[insertIndex+1]  = array[insertIndex];
                insertIndex--;
            }
            //找到需要插入的位置
            if(insertIndex+1!=i){
                array[insertIndex+1] = insertVal;
            }
        }
    }
}
