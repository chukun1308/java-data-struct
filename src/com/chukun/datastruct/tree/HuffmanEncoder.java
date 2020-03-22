package com.chukun.datastruct.tree;

import java.util.*;

/**
  *@auther : chukun
  *@description : 哈夫曼编码,赫夫曼树的基本应用，赫夫曼压缩编码
  *@create : 2019/7/10 23:51
  *@copyright www.hualala.com
  */
public class HuffmanEncoder {

    private static StringBuilder stringBuilder = new StringBuilder();
    private static Map<Byte,String> huffmanCodeMap = new HashMap<>();
    public static void main(String[] args) {
        String code = "i like like java so so much";
        //获取字符串的字节数组
        byte[] bytes = code.getBytes();

//        List<HuffmanNode> nodes = getCodeFrequency(bytes);
//        HuffmanNode huffmanRootNode = getHuffmanRootNode(nodes);
//        generatorHuffmanCodeTable(huffmanRootNode);
//        if(huffmanCodeMap!=null && huffmanCodeMap.size()>0){
//            System.out.println("生成的赫夫曼编码: "+huffmanCodeMap);
//        }
        byte[] resultBytes = huffmanZip(bytes);
        System.out.println("Huffman编码之后: "+Arrays.toString(resultBytes));

        //解码
        byte[] originByes = decoder(resultBytes, huffmanCodeMap);
        System.out.println("解码: "+new String(originByes));
    }

    /**
     * 字符数组解码
     * @param bytes
     * @param huffmanCodeMap
     * @return
     */
    public static byte[] decoder(byte[] bytes,Map<Byte,String> huffmanCodeMap){
        StringBuilder resultBuilder = new StringBuilder();
        for(int i=0;i<bytes.length;i++){
            byte b = bytes[i];
            boolean isLastIndex = (i==bytes.length-1);
            resultBuilder.append(getHuffmanBits(isLastIndex, b));
        }

        //对编码表反转
        Map<String,Byte> reversedHuffmanCodeMap = new HashMap<>();
        for(Map.Entry<Byte,String> entry:huffmanCodeMap.entrySet()){
            reversedHuffmanCodeMap.put(entry.getValue(),entry.getKey());
        }
        List<Byte> resultBytes = new ArrayList<>();
        //遍历生成的bit字符串
        for(int i=0;i<resultBuilder.length();){
            int count = 1;
            Byte b = null;
            boolean isFindByte = false;
            while(!isFindByte){
                String bitStrinfg = resultBuilder.substring(i, i + count);
                if(reversedHuffmanCodeMap.containsKey(bitStrinfg)){
                    b = reversedHuffmanCodeMap.get(bitStrinfg);
                    isFindByte = true;
                }else{
                    count++;
                }
            }
            resultBytes.add(b);
            i +=count;
        }

        //将list转为byte的字节数组
        byte[] resultByteArray = new byte[resultBytes.size()];
        for (int i = 0; i < resultBytes.size(); i++) {
            resultByteArray[i] = resultBytes.get(i);
        }

        return resultByteArray;
    }

    /**
     * 获取数字的二进制编码
     * @param flag 是否最后一位
     * @param b
     * @return
     */
    private static String getHuffmanBits(boolean flag,byte b){
        int temp = b;
        if(!flag){
            temp |=256;
        }
        String binaryString = Integer.toBinaryString(temp);
        if(!flag){
            return binaryString.substring(binaryString.length()-8);
        }else{
            //最后一位不截取8位，主要就是生成最后一位的bit 字符串，没按8位处理，所以导致最后一位可能不足8位
            return binaryString;
        }
    }

    /**
     * 生成赫夫曼编码
     * @param root
     */
    private static void generatorHuffmanCodeTable(HuffmanNode root){
        if(root==null){
            return;
        }
        if(root.getLeft()!=null){
            buildHuffmanCodeTable(root.getLeft(),"0",stringBuilder);
        }
        if(root.getRight()!=null){
            buildHuffmanCodeTable(root.getRight(),"1",stringBuilder);
        }
    }

    /**
     * Huffman压缩
     * @param originBytes
     * @return
     */
    public static byte[] huffmanZip(byte[] originBytes){
        if(originBytes==null || originBytes.length==0){
            throw  new IllegalArgumentException("原始编码的字节数组为空");
        }
        List<HuffmanNode> nodes = getCodeFrequency(originBytes);
        HuffmanNode huffmanRootNode = getHuffmanRootNode(nodes);
        generatorHuffmanCodeTable(huffmanRootNode);
        if(huffmanCodeMap==null || huffmanCodeMap.size()==0){
            throw  new RuntimeException("生成Huffman编码表错误");
        }
        byte[] bytes = huffmanZip(originBytes, huffmanCodeMap);
        return bytes;
    }

    /**
     * 获取每个byte出现的次数
     * @param bytes
     * @return
     */
    private static List<HuffmanNode> getCodeFrequency(byte[] bytes){
        Map<Byte,Integer> codeMap = new HashMap<>();
        List<HuffmanNode> resultList = new ArrayList<>();
        for(Byte b:bytes){
            if(codeMap.containsKey(b)){
                Integer count = codeMap.get(b);
                codeMap.put(b,count+1);
            }else{
                codeMap.put(b,1);
            }
        }
        //遍历map
        codeMap.forEach((k,v)->{
            HuffmanNode huffmanNode = new HuffmanNode(k,v);
            resultList.add(huffmanNode);
        });
        return resultList;
    }

    /**
     * 赫夫曼树压缩
     * @param originBytes
     * @param huffmanEncoderCodes
     * @return
     */
    private static byte[] huffmanZip(byte[] originBytes,Map<Byte,String> huffmanEncoderCodes){

        //先获取编码后的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b:originBytes){
            stringBuilder.append(huffmanEncoderCodes.get(b));
        }
        //8位编码一个byte
        int length;
        if(stringBuilder.length()%8==0){
            length = stringBuilder.length()/8;
        }else{
            length = stringBuilder.length()/8+1;
        }

        byte[] resultBytes = new byte[length];

        int index=0;
        for(int i=0;i<stringBuilder.length();i+=8){
            String String2byte;
            if((i+8)>stringBuilder.length()){
                String2byte = stringBuilder.substring(i);
            }else{
                String2byte = stringBuilder.substring(i,i+8);
            }
            resultBytes[index] = (byte)Integer.parseInt(String2byte,2);
            index++;
        }
        return resultBytes;
    }

    /**
     * 生成赫夫曼树的编码表
     * @param node
     * @param code
     * @param sb
     */
    private static void buildHuffmanCodeTable(HuffmanNode node,String code,StringBuilder sb){
        StringBuilder coderBuilder = new StringBuilder(sb);
        if(node==null){
            return;
        }
        //将code加入到 coderBuilder
        coderBuilder.append(code);
        if(node.getData()==null){
            //不是叶子节点
            if(node.getLeft()!=null){
                //向左构建
                buildHuffmanCodeTable(node.getLeft(),"0",coderBuilder);
            }
            if(node.getRight()!=null){
                buildHuffmanCodeTable(node.getRight(),"1",coderBuilder);
            }
        }else{
            huffmanCodeMap.put(node.getData(),coderBuilder.toString());
        }
    }

    /**
     * 创建赫夫曼树
     * @param nodes
     * @return
     */
    private static HuffmanNode getHuffmanRootNode(List<HuffmanNode> nodes){
        while (nodes.size()>1){
            Collections.sort(nodes);
            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);
            HuffmanNode parent = new HuffmanNode(null,leftNode.getWeight()+rightNode.getWeight());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}

/**
 * 哈夫曼编码的数据结构定义
 */
class HuffmanNode implements Comparable<HuffmanNode>{
    Byte data;
    Integer weight;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Byte data, Integer weight) {
        this.data = data;
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.weight-o.getWeight();
    }
}