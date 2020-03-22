package com.chukun.datastruct.sort;

import java.util.Arrays;

/**
  *@auther : chukun
  *@description : 堆排序
  *@create : 2019/7/7 20:21
  *@copyright www.hualala.com
  */
public class HeapSort {

    /**
     *     a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
     *
     * 　　b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
     *
     * 　　c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {4,8,9,-1,3,11};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }


    /**
     * 堆排序
     * @param array
     */
    public static void heapSort(int [] array){
        //1.构建一个大顶堆
        for(int i=array.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(array,i,array.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=array.length-1;j>0;j--){
            //将堆顶元素与末尾元素进行交换
            int temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            //重新对堆进行调整
            adjustHeap(array,0,j);
        }
    }
    /**
     *
     * @param array
     * @param i     需要调整的非叶子节点的下标
     * @param length 需要调整的数组的size
     */
    public static void adjustHeap(int[] array,int i,int length){
        if(array==null || array.length==0){
            System.out.println("数组为空，无法构建大顶堆");
            return;
        }
        //先取出当前元素i
        int temp = array[i];
        //从i结点的左子结点开始，也就是2i+1处开始
        for(int j=2*i+1;j<length;j=2*j+1){
            if(j+1<length && array[j+1]>array[j]){
                //如果左子结点小于右子结点，k指向右子结点
                j++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if(array[j]>temp){
                //交换
                array[i] = array[j];
                i=j;
            }else{
                break;
            }
        }
        //循环结束后，将temp放到调整之后的值上
        array[i] = temp ;
    }
}
