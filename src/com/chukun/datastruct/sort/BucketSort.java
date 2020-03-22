package com.chukun.datastruct.sort;

import java.util.Arrays;

/**
  *@auther : chukun
  *@description : 桶排序的基本实现
  *@create : 2019/6/30 23:20
  *@copyright www.hualala.com
  */
public class BucketSort {

    public static void main(String[] args) {
        int[] array = new int[]{823,215,13,11,79,86,999,7896,15698,7};
        bucketSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 桶排序的基本实现
     * @param array
     */
    public static void bucketSort(int[] array) {

        //定义一个二维数组,表示桶
        int[][] bucket = new int[10][array.length];
        //定义一个一维数组，表示每个桶里有几个数据
        //例如 countOfElements[0]记录的就是bucket[0]里面的元素个数
        int[] countOfElements = new int[array.length];

        //1.先找出数组中最大的数字
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        //计算最大的数字的位数
        int maxLength = (maxValue + "").length();
        //进行桶排序
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < array.length; j++) {
                //获取对应的位数的数字
                int ele = array[j] / n % 10;
                //将这个元素放入对应的桶中
                bucket[ele][countOfElements[ele]] = array[j];
                countOfElements[ele]++;
            }
            //按照桶的顺序，依次取出对应的数字
            int index = 0;
            for (int k = 0; k < countOfElements.length; k++) {
                if (countOfElements[k] != 0) {
                    for (int z = 0; z < countOfElements[k]; z++) {
                        //将取出的数据放入原数组
                        array[index++] = bucket[k][z];
                    }
                }
                //最后，一定要清除每个countOfElements的数据
                countOfElements[k] = 0;
            }
        }
    }
}
