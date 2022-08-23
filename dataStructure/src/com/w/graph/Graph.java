package com.w.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    public static void main(String[] args) {
        
        // String[] vertexs = {"A", "B", "C", "D", "E"};
        String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};

        //创建图对象
        Graph graph = new Graph(vertexs.length);
        //添加顶点
        Arrays.stream(vertexs).forEach(item -> {
            graph.addVertex(item);
        });
        
        //添加边
        // graph.addEdge(0, 1, 1);
        // graph.addEdge(0, 2, 1);
        // graph.addEdge(1, 2, 1);
        // graph.addEdge(1, 3, 1);
        // graph.addEdge(1, 4, 1);

        //添加边
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(3, 7, 1);
        graph.addEdge(4, 7, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 1);
        graph.addEdge(5, 6, 1);

        //显示邻接矩阵
        graph.showGraph();
        //深度优先遍历
        System.out.println("深度优先~");
        graph.depthFirstSearch();
        //广度优先遍历
        System.out.println("广度优先~");
        graph.broadFirstSearch();
    }
    
    //存储顶点集合
    private List<String> vertexList;
    //存储对应的邻接矩阵
    private int[][] edges;
    //表示边的数目
    private int numOfEdges;
    //判断节点是否已访问
    private boolean[] isVisit;

    //构造器
    public Graph(int n) {
        //初始化
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
    }

    //获取第一个邻接节点的下标
    public int getFirstNeighbor(int index) {

        for (int i = 0; i < edges[index].length; i++) {
            //存在
            if (edges[index][i] > 0) {
                return i;
            }
        }
        //不存在
        return -1;
    }

    //根据前一个邻接节点获取下一个邻接节点
    /**
     * 
     * @param v1 当前节点下标
     * @param v2 当前节点邻接下标
     * @return 返回邻接节点的下一个邻接节点，没有返回-1
     */
    public int getNextNeighbor(int v1, int v2) {

        for (int i = v2 + 1; i < edges[v1].length; i++) {
            //存在
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        //不存在
        return -1;
    }

    //深度优先遍历(对单个节点)
    public void depthFirstSearch(int index) {

        //处理当前节点
        System.out.print("=>" + getValueByIndex(index));
        //标记节点为已访问
        isVisit[index] = true;
        //获取第一个邻接节点
        int firstNeighbor = getFirstNeighbor(index);
        //判断邻接节点是否存在
        while (firstNeighbor != -1) {//存在邻接节点
            //判断节点是否被访问
            if (!isVisit[firstNeighbor]) {
                //未被访问，递归
                depthFirstSearch(firstNeighbor);
            }
            //获取邻接节点的下一个邻接节点，并赋值给firstNeighbor
            firstNeighbor = getNextNeighbor(index, firstNeighbor);
        }
    }

    //重载深度优先遍历(对所有节点)
    public void depthFirstSearch() {

        //初始化isVisit数组
        isVisit = new boolean[vertexList.size()];
        //遍历所有顶点(回溯)
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisit[i]) {
                //该节点没有被访问
                //回溯
                depthFirstSearch(i);
            }
        }
        System.out.println();
    }
    
    //广度优先遍历(对单个节点)
    public void broadFirstSearch(int index) {

        //处理当前节点
        System.out.print("=>" + getValueByIndex(index));
        //标记为已访问
        isVisit[index] = true;
        //创建一个队列，用LinkedList代替
        LinkedList<Integer> queue = new LinkedList<>();
        //入队列
        queue.addLast(index);
        //判断队列是否为空
        while (!queue.isEmpty()) {//队列不为空
            //出队列，取出队列头节点
            Integer headNode = queue.removeFirst();
            //获取头节点的第一个邻接节点
            int firstNeighbor = getFirstNeighbor(headNode);
            //判断邻接节点是否存在
            while (firstNeighbor != -1) {//存在
                //判断节点是否被访问
                if (!isVisit[firstNeighbor]) {//未被访问
                    //处理当前节点
                    System.out.print("=>" + getValueByIndex(firstNeighbor));
                    //标记为已访问
                    isVisit[firstNeighbor] = true;
                    //入队列
                    queue.addLast(firstNeighbor);
                }
                //获取邻接节点的下一个邻接节点，并赋值给firstNeighbor
                firstNeighbor = getNextNeighbor(headNode, firstNeighbor);
            }
        }
    }

    //重载广度优先遍历(对所有节点)
    public void broadFirstSearch() {

        //初始化isVisit数组
        isVisit = new boolean[vertexList.size()];
        //遍历所有顶点(回溯)
        for (int i = 0; i < vertexList.size(); i++) {
            //判断该顶点是否被访问
            if (!isVisit[i]) {
                //没有被访问
                //回溯
                broadFirstSearch(i);
            }
        }
        System.out.println();
    }

    //返回顶点的个数
    public int getNumOfVertexs() {

        return vertexList.size();
    }

    //显示图对应的邻接矩阵
    public void showGraph() {

        //遍历邻接矩阵
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    //返回边的数量
    public int getNumOfEdges() {

        return numOfEdges;
    }

    //返回顶点下标对应的数据
    public String getValueByIndex(int index) {

        return vertexList.get(index);
    }

    //返回两个顶点间的权值
    public int getWeight(int v1, int v2) {

        return edges[v1][v2];
    }

    //添加顶点
    public void addVertex(String vertex) {

        vertexList.add(vertex);
    }

    //添加边
    /**
     * @param v1 表示顶点的下标，从零开始，例如：A -> 0, B -> 1
     * @param v2
     * @param weight 权值：表示两个顶点间是否有连接，0无，1有
     */
    public void addEdge(int v1, int v2, int weight) {

        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
