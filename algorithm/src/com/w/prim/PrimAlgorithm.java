package com.w.prim;

import java.util.Arrays;

public class PrimAlgorithm {

    public static void main(String[] args) {
        
        //顶点数组
        char[] data = {'A','B','C','D','E','F','G'};
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
		int [][]weight={
            {10000,5,7,10000,10000,10000,2},
            {5,10000,10000,9,10000,10000,3},
            {7,10000,10000,10000,8,10000,10000},
            {10000,9,10000,10000,10000,4,10000},
            {10000,10000,8,10000,10000,5,4},
            {10000,10000,10000,4,5,10000,6},
            {2,3,10000,10000,4,6,10000}
        };
        int[][] copy = new int[data.length][data.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = Arrays.copyOf(weight[i], copy[i].length);
        }
        copy[0][0] = 1;
        for (int[] item : weight) {
            System.out.println(Arrays.toString(item));
        }
        // Graph graph = new Graph(data, weight);
        // graph.showGraph();
        // MinimumTree minimumTree = new MinimumTree(graph);
        // minimumTree.prim(0);
    }
}

//最小生成树
class MinimumTree {

    Graph graph;

    //构造器
    public MinimumTree (Graph graph) {

        this.graph = graph;
    }

    //编写prim算法，得到最小生成树
    public void prim (int vertex) {

        //定义一个数组，标记顶点是否被访问过，0表示未访问，1表示已访问
        int[] visited = new int[graph.vertexs];
        //处理当前顶点，标记为已访问
        visited[vertex] = 1;
        //记录两个顶点的下标
        int vertex1 = -1;
        int vertex2 = -1;
        //两个顶点间边的最小权值
        int minimumWeight = 10000;
        //最小生成树共有n - 1条边
        for (int edge = 1; edge < graph.vertexs; edge++) {
            //遍历已访问的顶点
            for (int i = 0; i < graph.vertexs; i++) {
                //遍历未被访问的顶点
                for (int j = 0; j < graph.vertexs; j++) {
                    //两个顶点相同，即为同一个顶点
                    if (i == j) {
                        continue;
                    }
                    //判断i是否是已访问，j是否是未访问，两个顶点间的权值大小与最小权值比较
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minimumWeight) {
                        minimumWeight = graph.weight[i][j];
                        vertex1 = i;
                        vertex2 = j;
                    }
                }
            }
            //打印权值最小的边
            System.out.println("<" + graph.data[vertex1] + "," + graph.data[vertex2] + ">," + minimumWeight);
            //将vertex2标记为已访问
            visited[vertex2] = 1;
            //重置minimumWeight
            minimumWeight = 10000;
        }
    }
}

//创建图
class Graph {

    int vertexs;//顶点个数
    char[] data;//存放于顶点的数据
    int[][] weight;//存放边的权值

    //构造器
    public Graph (char[] data, int[][] weight) {

        this.vertexs = data.length;
        this.data = data;
        this.weight = weight;
    }

    //显示图的邻接矩阵
    public void showGraph() {

        //遍历weight数组
        for (int[] link : weight) {
            System.out.println(Arrays.toString(link));
        }
    }
}