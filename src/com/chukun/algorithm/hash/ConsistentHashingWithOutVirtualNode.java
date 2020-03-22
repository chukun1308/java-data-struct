package com.chukun.algorithm.hash;

import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author chukun
 * 实现一致性hash算法，不带虚拟节点的一致性hash
 */
public class ConsistentHashingWithOutVirtualNode {

    /**
     * 服务器的地址列表
     */
   private static  String[] servers = {"192.168.60.100:8080","192.168.60.101:8080","192.168.60.102:8080"};

    /**
     * key表示服务器的hash值，value表示服务器的名称
     */
   private static SortedMap<Integer,String> sortedMap = new ConcurrentSkipListMap<>();

   static {
       for (String server : servers) {
           int hash = hash(server);
           System.out.println("["+server+"] 加入到集合中，hash值为: "+hash);
           sortedMap.put(hash,server);
       }
   }


    /**
     * 返回一致性hash的server node
     * @param node
     * @return
     */
   public static String getServer(String node){
       int hash = hash(node);
       //得到大于该Hash值的所有Map
       SortedMap<Integer, String> subMap = sortedMap.headMap(hash);
       //第一个Key就是顺时针过去离node最近的那个结点
       int index = subMap.firstKey();
       // 返回对应的服务器名称
       return subMap.get(index);
   }


    /**
     * 使用 FNV1_32_HASH 算法，该算法证实下来散列分布比较均匀，hash 碰撞尚且 ok
     * @param key
     * @return
     */
   private static int hash(String key){
       final  int  p  =  16777619;
       int  hash  =  (int)2166136261L;
       for(int  i  =  0;  i  <  key.length();  i++)  {
           hash  =  (hash  ^  key.charAt(i))  *  p;
       }
       hash  +=  hash  <<  13;
       hash  ^=  hash  >>  7;
       hash  +=  hash  <<  3;
       hash  ^=  hash  >>  17;
       hash  +=  hash  <<  5;
       // 如果算出来的值为负数则取其绝对值
       if  (hash  <  0)  {
           hash  =  Math.abs(hash);
       }
       return  hash;
   }


    public static void main(String[] args) {

       String [] nodes = {"172.16.32.75:1111","172.16.32.76:2222","172.16.32.77:3333"};
        for (String node : nodes) {
            System.out.println("["+node+"] 的hash 值为"+hash(node)+"，被路由到: "+getServer(node));
        }
    }
}
