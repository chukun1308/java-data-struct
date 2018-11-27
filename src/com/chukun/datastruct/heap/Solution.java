package com.chukun.datastruct.heap;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Solution {

    private class Frequency implements Comparable<Frequency>{
        public int e,freq;

        public Frequency(int e,int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Frequency o) {
            if(this.freq<o.freq){
                return -1;
            }
            else if(this.freq > o.freq) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer,Integer> map = new TreeMap<>();
        //将数组每个数字与出现的频次，做成map映射
        for(int num:nums){
            if(map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else{
                map.put(num,1);
            }
        }
        //基于最大堆实现的优先队列，值越大，优先级越高
        PriorityQueue<Frequency> pq = new PriorityQueue<>();
        for(int key:map.keySet()){
            if(map.size()<k){
                pq.enqueue(new Frequency(key,map.get(key)));
            }else if(map.get(key)>pq.getFront().freq){
                pq.dequeue();
                pq.enqueue(new Frequency(key,map.get(key)));
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while(!pq.isEmpty())
            res.add(pq.dequeue().e);
        return res;
    }
}
