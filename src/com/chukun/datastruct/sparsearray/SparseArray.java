package com.chukun.datastruct.sparsearray;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author chukun
 * 稀疏数组例子
 */
public class SparseArray {

    public static void main(String[] args) throws FileNotFoundException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("map.data")));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("map.data")));

        int colum = 11;
        int rows = 11;
        int[][] originArray = new int[colum][rows];
        originArray[1][2] = 2;
        originArray[2][3] = 4;
        originArray[5][4] = 8;
        //打印原始的数组
//        for(int[] datas:originArray){
//            for(int item:datas){
//                System.out.printf("%d\t",item);
//            }
//            System.out.println();
//        }

        //构建稀疏数组
        int sum = 0;
        for(int[] datas:originArray){
            for(int item:datas){
                if(item!=0){
                    sum++;
                }
            }
        }

        //构造稀疏数组
        int [][] sparseArray = new int[sum+1][3];
        sparseArray[0][0] = colum;
        sparseArray[0][1] = rows;
        sparseArray[0][2] = sum;

        int count=0;
        for(int i=0;i<originArray.length;i++){
            for(int j=0;j<originArray[i].length;j++){
                if(originArray[i][j]!=0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = originArray[i][j];
                }
            }
        }

        //打印稀疏数组
        for(int[] data:sparseArray){
            for(int item:data){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        //恢复原始数组
        int originColumn = sparseArray[0][0];
        int originRow = sparseArray[0][1];
        int [][] buildArray = new int[originColumn][originRow];
        for(int i=1;i<sparseArray.length;i++){
            buildArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        //打印恢复之后的数组
        for(int[] data:buildArray){
            for(int item:data){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
    }
}
