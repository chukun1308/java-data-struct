package com.chukun.algorithm.prim;

import java.util.Arrays;

/**
  *@auther : chukun
  *@description : 普里姆算法之最小生成树
  *@create : 2019/7/31 0:33
  *@copyright www.hualala.com
  */
public class PrimAlgorithmOfMinTree {

    public static void main(String[] args) {
        //测试看看图是否创建ok
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int vertexs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};

        MGraph graph = new MGraph(vertexs);
        MinTree minTree = new MinTree();
        //创建邻接矩阵
        minTree.createGraph(graph,vertexs,data,weight);
        //展示邻接矩阵
        minTree.showGraph(graph);

        //普里姆算法
        minTree.prim(graph,0);
    }
}



class MinTree{

    /**
     * 创建邻接矩阵
     * @param graph
     * @param vertex
     * @param datas
     * @param weights
     */
    public void createGraph(MGraph graph,int vertex,char[] datas,int[][] weights){
        for(int i=0;i<vertex;i++){
            graph.datas[i] = datas[i];
            for(int j=0;j<vertex;j++){
                graph.weights[i][j] = weights[i][j];
            }
        }
    }

    /**
     * 展示邻接矩阵
     * @param graph
     */
    public void showGraph(MGraph graph){
        for(int[] link:graph.weights){
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     *
     * @param graph 生成的连通图
     * @param vertex 表示从哪个节点生成最小生成树
     */
    public void prim(MGraph graph,int vertex){
         //首先定义一个访问的数组
        int[] visited = new int[graph.vertex];
        //将当前节点置为已经访问 1--->表示已经访问
        visited[vertex] = 1;
        //定义最小的数的权重
        int minWeight =10000;
        //h1 和 h2 记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        //遍历每一条边，因为有 graph.verxs顶点，普利姆算法结束后，有 graph.verxs-1边
        for(int k=1;k<graph.vertex;k++) {
            //这个是确定每一次生成的子图 ，和哪个结点的距离最近
            for (int i = 0; i < graph.vertex; i++) {// i结点表示被访问过的结点
                for (int j = 0; j < graph.vertex; j++) {//j结点表示还没有访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weights[i][j] < minWeight) {
                        //替换minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
                        minWeight = graph.weights[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<" + graph.datas[h1] + "," + graph.datas[h2] + "> 权值: " + graph.weights[h1][h2]);
            //将最小的权值重置
            minWeight = 10000;
            //将h2标记为已经访问过
            visited[h2] = 1;
        }
    }
}

/**
 * 创建图，邻接矩阵实现
 */
class MGraph{

    //顶点个数
     int vertex;
    //节点数据
     char[] datas;

    //存放边
     int[][] weights;

    public MGraph(int vertex){
        this.vertex = vertex;
        this.datas = new char[vertex];
        this.weights = new int[vertex][vertex];
    }
}
