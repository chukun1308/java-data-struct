package com.chukun.algorithm.greedy;

import java.util.*;

/**
  *@auther : chukun
  *@description : 贪心算法的基本用法
  *@create : 2019/7/30 21:20
  *@copyright www.hualala.com
  */
public class GreedyAlgorithm {

    public static void main(String[] args) {

        //创建电台，放入map
        Map<String, Set<String>>  broadcasts = new HashMap<>();
        //k1覆盖的地区
        Set<String> k1Set = new HashSet<>();
        k1Set.add("北京");
        k1Set.add("上海");
        k1Set.add("天津");
        //k2覆盖的地区
        Set<String> k2Set = new HashSet<>();
        k2Set.add("北京");
        k2Set.add("广州");
        k2Set.add("深圳");
        //k3覆盖的地区
        Set<String> k3Set = new HashSet<>();
        k3Set.add("成都");
        k3Set.add("上海");
        k3Set.add("杭州");
        //k4覆盖的地区
        Set<String> k4Set = new HashSet<>();
        k4Set.add("上海");
        k4Set.add("天津");
        //k5覆盖的地区
        Set<String> k5Set = new HashSet<>();
        k5Set.add("杭州");
        k5Set.add("大连");
        //加入到broadcasts的map中
        broadcasts.put("k1",k1Set);
        broadcasts.put("k2",k2Set);
        broadcasts.put("k3",k3Set);
        broadcasts.put("k4",k4Set);
        broadcasts.put("k5",k5Set);

        //存放所有的地区
        Set<String> allAreas = new HashSet<>();
        broadcasts.values().forEach(p->{
            allAreas.addAll(p);
        });
        //存放选择的电台集合
        List<String> selects = new ArrayList<>();
        //定义一个临时集合，存放遍历过程中电台覆盖的地区与地区的交集
        Set<String> tempSet = new HashSet<>();
        //定义一个maxKey,保存在一次遍历中，能够覆盖的最大地区的key
        String maxKey = null;
        while (allAreas.size()!=0){
            //每次循环一次，需要把maxKey置为空
            maxKey = null;
            //遍历broadcasts
            for(String key:broadcasts.keySet()){
                //临时集合置为空
                tempSet.clear();
                //得到地区
                Set<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求交集
                tempSet.retainAll(allAreas);
                //这里就是贪心算法的特点，每次都选择最优的
                if(tempSet.size()>0 && (maxKey==null || tempSet.size()>broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if(maxKey!=null){
                selects.add(maxKey);
                //将maxKey覆盖的地区从allAreas去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("选择的广播: "+selects);
    }
}
