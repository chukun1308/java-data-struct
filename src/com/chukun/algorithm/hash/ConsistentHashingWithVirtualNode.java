package com.chukun.algorithm.hash;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author chukun
 * 实现一致性hash算法，带虚拟节点的一致性hash
 *
 * 1、一个真实结点如何对应成为多个虚拟节点？
 *
 * 2、虚拟节点找到后如何还原为真实结点？
 *
 * 这两个问题其实有很多解决办法，这里使用了一种简单的办法，给每个真实结点后面根据虚拟节点加上后缀再取Hash值，
 * 比如”192.168.0.0:111″就把它变成”192.168.0.0:111&&VN0″到”192.168.0.0:111&&VN4″，
 * VN就是Virtual Node的缩写，还原的时候只需要从头截取字符串到”&&”的位置就可以了。
 */
public class ConsistentHashingWithVirtualNode {

    /**
     * 服务器的地址列表
     */
   private static  String[] servers = {"192.168.60.100:8080","192.168.60.101:8080","192.168.60.102:8080"};

    /**
     * 虚拟节点的连接符
     */
   private static final String SERVER_DELIMITER = "&&VN";

    /**
     * 虚拟节点分割符
     */
   private static final String VIRTUAL_NODE_DELIMITER = "&&";

    /**
     * 真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，使用LinkedList会更好
     */
    private static List<String> realNodes = new LinkedList<>();

    /**
     * 这里先把虚拟节点的个数写死
     */
   private static final int VIRTUAL_NODES = 5;

    /**
     * key表示服务器的hash值，value表示服务器的名称
     */
   private static SortedMap<Integer,String> sortedMap = new ConcurrentSkipListMap<>();

   static {
       for (String server : servers) {
           // 先把原始的服务器添加到真实结点列表中
           realNodes.add(server);
       }
       //给每个节点添加5个虚拟节点
       for(String node:realNodes){
           for(int i=0;i<VIRTUAL_NODES;i++){
               String virtualName = String.format("%s%s%s",node,SERVER_DELIMITER,i);
               int hash = hash(virtualName);
               System.out.println("虚拟节点[" + virtualName + "]被添加, hash值为" + hash);
               sortedMap.put(hash,virtualName);
           }
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
       SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
       //第一个Key就是顺时针过去离node最近的那个结点
       int index = subMap.firstKey();
       // 返回对应的虚拟节点名称，这里字符串稍微截取一下
       String virtualNode = subMap.get(index);
       return virtualNode.substring(0,virtualNode.indexOf(VIRTUAL_NODE_DELIMITER));
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
