package com.w.floyd;

import java.util.Arrays;

public class FloydAlgorithm {
    
    public static void main(String[] args) {
        
        // 测试看看图是否创建成功
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };
        Graph graph = new Graph(vertex, matrix);
        graph.floyd();
        graph.show();

    }
}

//创建图
class Graph {

    char[] vertex;//顶点数据
    int[][] distance;//保存各个顶点间的距离
    int[][] pre_vertex;//保存到达目标顶点的前驱顶点

    //构造器
    public Graph (char[] vertex, int[][] matrix) {

        this.vertex = vertex;
        this.distance = matrix;
        this.pre_vertex = new int[vertex.length][vertex.length];
        //初始化pre_vertex数组
        for (int i = 0; i < pre_vertex.length; i++) {
            Arrays.fill(pre_vertex[i], i);
        }
    }

    //floyd算法
    public void floyd() {

        //保存两个顶点之间的距离
        int length = 0;
        //遍历中间顶点
        for (int m = 0; m < vertex.length; m++) {
            //从i顶点出发
            for (int i = 0; i < vertex.length; i++) {
                //到达j顶点
                for (int j = 0; j < vertex.length; j++) {
                    length = distance[i][m] + distance[m][j];
                    //比较length的大小
                    if (length < distance[i][j]) {
                        //改变i -> j 顶点的距离
                        distance[i][j] = length;
                        //改变j顶点前驱顶点
                        pre_vertex[i][j] = m;
                    }
                }
            }
        }
    }

    //遍历distance数组和pre_vertex数组
    public void show () {

        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[i].length; j++) {
                System.out.print(vertex[i] + "->" + vertex[j] + ": " + distance[i][j] + " ");
            }
            System.out.println();
        }
        
        for (int i = 0; i < pre_vertex.length; i++) {
            for (int j = 0; j < pre_vertex[i].length; j++) {
                System.out.print(vertex[pre_vertex[i][j]] + " ");
            }
            System.out.println();
        }
    }
}
