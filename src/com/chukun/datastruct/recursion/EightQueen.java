package com.chukun.datastruct.recursion;

/**
 * @author chukun
 * 八皇后问题
 */
public class EightQueen {

    int queenNum = 8;
    //一维数组的含义，表示第 i+1 (下标) 个皇后放在 (i+1) 行 (queen[i]+1)列
    int[] queen = new int[queenNum];
    public static void main(String[] args) {
        EightQueen queen = new EightQueen();
        queen.setQueen(0);
    }

    /**
     * 打印八皇后的数组
     */
    public  void print(){
        for(int i=0;i<queen.length;i++){
            System.out.print(queen[i]+" ");
        }
        System.out.println();
    }

    /**
     * 判断放置第n个皇后是否冲突
     * @param n
     * @return
     */
    public boolean judge(int n){
        for(int i=0;i<n;i++){
           //判断每个皇后是否在同一列
           if(queen[i]==queen[n]){
               return false;
           }
           //判断每个皇后是否在一个对角线上
           else if(Math.abs(n-i)==Math.abs(queen[n]-queen[i])){
               return false;
           }
        }
        return true;
    }

    /**
     * 放置皇后
     * @param n
     */
    public void setQueen(int n){
        if(n==queenNum){
            //这时说明八个皇后已经放置完毕了
            print();
            return;
        }
        //依次放入皇后，并且判断是否会冲突
        for(int i=0;i<queenNum;i++){
            //先把当前这个皇后放在第i列上
            queen[n] = i;
            //判断放置当前皇后时，是否会冲突
            if(judge(n)){
                //不冲突,则开始放置n+1个皇后
                setQueen(n+1);
            }
        }
    }
}
