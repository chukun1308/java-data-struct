package com.chukun.algorithm.dac;

/**
  *@auther : chukun
  *@description : 汉诺塔，实现分治算法
  *@create : 2019/7/26 16:35
  *@copyright www.hualala.com
  */
public class HanoiTower {

    public static void main(String[] args) {
       divideAndConquerOfhanoiTow(5,'A','B','C');
    }

    /**
     * 汉诺塔问题，将A柱子上的所有碟子，由下到上，从大到小依次向上排列
     * @param num
     * @param pillarA
     * @param pillarB
     * @param pillarC
     */
    public static void divideAndConquerOfhanoiTow(int num,char pillarA,char pillarB,char pillarC){
        if(num==1){
            System.out.println("第一个盘子从"+pillarA+" ---> "+pillarC);
        }else{
            //先将num-1个碟子从pillarA移动到pillarB，借助pillarC
            divideAndConquerOfhanoiTow(num-1,pillarA,pillarC,pillarB);
            //将最后一个从pillarA移动到pillarC
            System.out.println("第"+num+"盘子从"+pillarA+" ---> "+pillarC);
            //再将num-1个碟子从pillarB移动到pillarC，借助pillarA
            divideAndConquerOfhanoiTow(num-1,pillarB,pillarA,pillarC);
        }
    }
}
