package com.chukun.algorithm.dynamic;

/**
 * @auther : chukun
 * @description : 0-1背包问题,使用动态规划，计算背包问题
 * @create : 2019/7/26 19:20
 * @copyright www.hualala.com
 */
public class KnapsackProblem {
    //背包的容量
    private static int bagWeight = 4;
    //物品的重量
    private static int[] weights = {1, 3, 2, 1};
    //对应的物品的价值
    private static int[] worths = {1500, 3000, 2000, 2000};
    //currentWorths[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
    private static int[][] currentWorths = new int[weights.length + 1][bagWeight + 1];
    //为了记录放入商品的情况，定一个二维数组
    private static int[][] currentGoods = new int[weights.length + 1][bagWeight + 1];

    static {
        //初始化第一行和第一列, 这里在本程序中，可以不去处理，因为默认就是0
        for (int i = 0; i < currentWorths.length; i++) {
            currentWorths[i][0] = 0; //将第一列设置为0
        }
        for (int i = 0; i < currentWorths[0].length; i++) {
            currentWorths[0][i] = 0; //将第一行设置0
        }
    }

    public static void main(String[] args) {
        handleKnapsackProblem();
        for (int i = 0; i < currentWorths.length; i++) {
            for (int j = 0; j < currentWorths[i].length; j++) {
                System.out.print(currentWorths[i][j] + " ");
            }
            System.out.println();
        }
        int i = currentGoods.length-1;
        int j = currentGoods[0].length-1;
        while(i>0 && j>0){
            if(currentGoods[i][j]==1){
                System.out.printf("第%d个商品放入背包\n",i);
                j-=weights[i-1];
            }
            i--;
        }
    }

    /**
     * 处理背包问题
     */
    private static void handleKnapsackProblem() {
        for (int i = 1; i < currentWorths.length; i++) {
            for (int j = 1; j < (bagWeight + 1); j++) {
                //程序i 是从1开始的，因此原来公式中的 w[i] 修改成 w[i-1]
                if (weights[i-1] > j) {
                    currentWorths[i][j] = currentWorths[i - 1][j];
                } else {
                    //说明:
                    //因为i 从1开始的， 因此公式需要调整成
                    //v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放到背包的情况，不能直接的使用上面的公式，需要使用if-else来体现公式
                    if (currentWorths[i - 1][j] < worths[i-1] + currentWorths[i - 1][j - weights[i - 1]]) {
                        currentWorths[i][j] = worths[i-1] + currentWorths[i - 1][j - weights[i - 1]];
                    } else {
                        currentWorths[i][j] = currentWorths[i - 1][j];
                    }
                    currentGoods[i][j] = 1;
                }
            }
        }
    }
}
