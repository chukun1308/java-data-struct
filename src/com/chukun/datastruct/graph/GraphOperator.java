package com.chukun.datastruct.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
  *@auther : chukun
  *@description : 图的基本操作,基于二维数组实现
  *@create : 2019/7/21 22:23
  *@copyright www.hualala.com
  */
public class GraphOperator {

    private List<String> vertexes;
    private int[][] edges;
    private int numOfEdges;
    /**
     * 判断当前节点是否被访问过
     */
    private boolean[] isVisited;

    public static void main(String[] args) {
        GraphOperator graphOperator = new GraphOperator(5);
        String[] vertexArray = {"A","B","C","D","E"};
        //添加节点
        for(String vertex:vertexArray){
            graphOperator.addVertex(vertex);
        }
        //添加边
        graphOperator.addEdges(graphOperator.getVertexIndex("A"),graphOperator.getVertexIndex("B"),1);
        graphOperator.addEdges(graphOperator.getVertexIndex("B"),graphOperator.getVertexIndex("C"),1);
        graphOperator.addEdges(graphOperator.getVertexIndex("A"),graphOperator.getVertexIndex("C"),1);
        graphOperator.addEdges(graphOperator.getVertexIndex("B"),graphOperator.getVertexIndex("D"),1);
        graphOperator.addEdges(graphOperator.getVertexIndex("B"),graphOperator.getVertexIndex("E"),1);

        graphOperator.showGraph();
        //深度优先
        //graphOperator.dfs();
        //广度优先
        graphOperator.bfs();

    }

    public GraphOperator(int n){
        vertexes = new ArrayList<>(n);
        edges = new int[n][n];
        isVisited = new boolean[n];
        numOfEdges = 0;
    }

    /**
     * 添加顶点
     * @param vertex
     */
    public void addVertex(String vertex){
        vertexes.add(vertex);
    }

    /**
     * 返回边的数量
     * @return
     */
    public int getNumOfEdges(){
        return numOfEdges;
    }

    /**
     * 返回订点的数量
     * @return
     */
    public int getNumOfVertex(){
        return vertexes.size();
    }

    /**
     * 返回顶点的下标
     * @param vertex
     * @return
     */
    public int getVertexIndex(String vertex){
        return vertexes.indexOf(vertex);
    }

    /**
     * 获取当前节点的第一个邻接节点
     * @param index
     * @return
     */
    private int getFirstVertexOfCurrentVertex(int index){
        for(int i=0;i<vertexes.size();i++){
            if(edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点获取下一个邻接节点
     * @param v1
     * @param v2
     * @return
     * 比如 B-->C 连接  B--->D 连接 访问了C 需要返回B找D的下标
     */
    private int getNextVertexOfPreVertex(int v1,int v2){
        for(int i=v2+1;i<vertexes.size();i++){
            if(edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 添加边
     * @param v1 第一个元素的下标
     * @param v2 第二个元素的下标
     * @param w  权重
     */
    public void addEdges(int v1,int v2,int w){
        edges[v1][v2] = w;
        edges[v2][v1] = w;
        numOfEdges++;
    }

    /**
     * 深度优先遍历,纵向遍历
     * @param isVisited
     * @param i
     */
    private void dfs(boolean[] isVisited,int i){
        System.out.print(vertexes.get(i)+"--->");
        //将当前访问的节点置为已访问
        isVisited[i] = true;
        int w = getFirstVertexOfCurrentVertex(i);
        while (w!=-1){
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果该节点已经访问过了，就获取当前这个节点的邻接节点
            w = getNextVertexOfPreVertex(i,w);
        }
    }

    /**
     * 深度优先遍历
     */
    public void dfs(){
        for(int i=0;i<vertexes.size();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    /**
     * 广度优先遍历
     */
    public void bfs(){
        for(int i=0;i<vertexes.size();i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    /**
     * 广度优先遍历
     * @param isVisited
     * @param i
     */
    private void bfs(boolean[] isVisited,int i){
        //邻接节点
        int w;
        //表示队列的头节点对应的下标
        int u;
        System.out.print(vertexes.get(i)+"==>");
        //将当前顶点设置为已经访问
        isVisited[i] = true;
        LinkedList<Integer> vertexQueue = new LinkedList<>();
        //加入到节点集合中，按照访问的顺序
        vertexQueue.addLast(i);
        while (!vertexQueue.isEmpty()){
            //取出头结点的下标
            u = vertexQueue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstVertexOfCurrentVertex(i);
            while (w!=-1) {
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(vertexes.get(w)+"==>");
                    //标记已经访问
                    isVisited[w] = true;
                    //加入到访问的集合
                    vertexQueue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接节点
                w = getNextVertexOfPreVertex(u,w);
            }
        }
    }

    /**
     * 展示图
     */
    public void showGraph(){
        for(int i=0;i<edges.length;i++){
            for(int j=0;j<edges[i].length;j++){
                System.out.print(edges[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
