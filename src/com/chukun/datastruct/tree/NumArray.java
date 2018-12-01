package com.chukun.datastruct.tree;
//https://leetcode.com/problems/range-sum-query-immutable/description/

/**
 * @author chukun
 */
public class NumArray {

    /**
     *  sum[i]存储前i个元素和, sum[0] = 0
     *  即sum[i]存储nums[0...i-1]的和
     *  sum(i, j) = sum[j + 1] - sum[i]
     */
    private int [] sums;

    public NumArray(int [] nums){
        sums = new int[nums.length+1];
        sums[0]=0;
        for(int i=1;i<sums.length;i++){
            sums[i]=sums[i-1]+nums[i-1];
        }
    }

    public  int sumRange(int i,int j){
        return sums[j+1]-sums[i];
    }

}
