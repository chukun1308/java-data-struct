package com.chukun.datastruct.recursion;

/**
 * @author chukun
 * 迷宫问题
 */
public class MazeRecursion {

    public static void main(String[] args) {

        int[][] map = new int[8][7];

        //现将上下左右为围上墙  1表示墙

        for(int i=0;i<7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for(int i=0;i<8;i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("构造地图成功");

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        findWay(map,1,1);
        System.out.println("走过迷宫之后的地图");
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 迷宫找路
     * @param map 地图
     * @param i   起始位置横坐标
     * @param j   起始位置纵坐标
     * 思路: 0--->表示还没走 2-->表示可以走通  3---> 死路，走不通
     * 寻路策略 上-->右--->下---->左
     */
    public static boolean findWay(int[][]map,int i,int j){
        if(map[6][5]==2){
            return true;
        }else{
            if(map[i][j]==0){
                map[i][j] = 2;//假设可以走通
                if(findWay(map,i-1,j)){
                    //向上走
                    return true;
                }else if(findWay(map, i, j+1)){
                    //向右走
                    return true;
                }else if(findWay(map, i+1, j)){
                    //向下走
                    return true;
                }else if(findWay(map, i, j-1)){
                    //向左走
                    return true;
                }else{
                    //走不通
                    map[i][j] = 3;
                }
            }else{
                return false;
            }
        }
        return false;
    }
}
