package com.chukun.datastruct.tree;

//https://leetcode.com/problems/range-sum-query-mutable/description/

/**
 * @author chukun
 * 线段树解决区间内元素变化，更新的问题
 */
public class MutableNumArray {

    private SegmentTree<Integer> segmentTree;
    public MutableNumArray(int [] nums){
        Integer data[] = new Integer[nums.length];
        for(int i=0;i<nums.length;i++){
            data[i] = nums[i];
        }
        segmentTree = new SegmentTree<>(data,(a,b)->a+b);
    }

    public void update(int i,int val){
        if(segmentTree==null){
            throw new IllegalArgumentException("Error");
        }
        segmentTree.set(i,val);
    }
    public int sumRange(int i, int j) {
        if(segmentTree == null)
            throw new IllegalArgumentException("Error");
        return segmentTree.query(i, j);
    }
}
