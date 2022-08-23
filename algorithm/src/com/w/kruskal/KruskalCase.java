package com.w.kruskal;

import java.util.Arrays;

public class KruskalCase {

    private static final int INF = Integer.MAX_VALUE; 
    
    public static void main(String[] args) {
        
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		//克鲁斯卡尔算法的邻接矩阵  
	    int matrix[][] = {
	      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
    /*A*/ {   0,  12, INF, INF, INF,  16,  14},
	/*B*/ {  12,   0,  10, INF, INF,   7, INF},
	/*C*/ { INF,  10,   0,   3,   5,   6, INF},
	/*D*/ { INF, INF,   3,   0,   4, INF, INF},
	/*E*/ { INF, INF,   5,   4,   0,   2,   8},
	/*F*/ {  16,   7,   6, INF,   2,   0,   9},
	/*G*/ {  14, INF, INF, INF,   8,   9,   0}
        }; 
        Graph graph = new Graph(data, matrix);
        graph.showGraph();
        Kruskal kruskal = new Kruskal(graph);
        kruskal.kruskal();
        System.out.println(Arrays.toString(kruskal.results));
    }
}

class Kruskal {

    Graph graph;
    int edgesNumber;
    EdgeData[] edges;
    EdgeData[] results;

    //构造器
    public Kruskal (Graph graph) {

        this.graph = graph;
        //遍历graph中的weight数组,计算边的数量
        for (int i = 0; i < graph.weight.length - 1; i++) {
            for (int j = i + 1; j < graph.weight[i].length; j++) {
                if (graph.weight[i][j] != Integer.MAX_VALUE) {
                    this.edgesNumber++;
                }
            }
        }
        this.edges = new EdgeData[edgesNumber];
        //遍历graph中的weight数组,初始化edges数组
        int index = 0;//记录edges数组的索引
        for (int i = 0; i < graph.weight.length - 1; i++) {
            for (int j = i + 1; j < graph.weight[i].length; j++) {
                if (graph.weight[i][j] != Integer.MAX_VALUE) {
                    edges[index++] = new EdgeData(graph.data[i], graph.data[j], graph.weight[i][j]);
                }
            }
        }
        this.results = new EdgeData[graph.vertexs - 1];
    }

    //将edges数组按权值大小排序(从小到大)
    public void sortEdges () {

        Arrays.sort(edges, (edge1, edge2) -> edge1.weight - edge2.weight);
    }

    //获取下标为i的顶点的终点,用于判断两个终点是否相同
    public int getEnd (int[] ends, int i) {

        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    //kruskal算法
    public void kruskal () {

        //定义一个ends数组,用于保存最小生成树的终点
        int[] ends = new int[graph.vertexs];
        //记录最小生成树边的数量
        int index = 0;
        //对edges数组排序
        sortEdges();
        //遍历所有边
        for (int i = 0; i < edgesNumber; i++) {
            //获取边的start索引
            int start = graph.getPosition(edges[i].start);
            //获取边的end索引
            int end = graph.getPosition(edges[i].end);
            //获取start在最小生成树的终点
            int end1 = getEnd(ends, start);
            //获取end在最小生成树的终点
            int end2 = getEnd(ends, end);
            //判断两个终点是否相同
            if (end1 != end2) {
                //不相同
                //将这条边加入到results数组
                results[index++] = edges[i];
                //设置end1的终点为end2
                ends[end1] = end2;
            }
        }
        System.out.println(Arrays.toString(ends));
    }
    
}

//表示一条边
class EdgeData {

    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//边的权值

    //构造器
    public EdgeData (char start, char end, int weight) {

        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EdgeData [<" + start + ", " + end + ">, weight=" + weight + "]\n";
    }

    
}

//创建图
class Graph {

    int vertexs;//顶点个数
    char[] data;//顶点数据
    int[][] weight;//边的权值

    //构造器
    public Graph (char[] data, int[][] weight) {

        this.vertexs = data.length;

        this.data = new char[vertexs];
        this.data = Arrays.copyOf(data, vertexs);

        this.weight = new int[vertexs][vertexs];
        for (int i = 0; i < weight.length; i++) {
            this.weight[i] = Arrays.copyOf(weight[i], vertexs);
        }
    } 

    //根据顶点数据返回对应的下标
    public int getPosition (char ch) {

        //遍历data数组
        for (int i = 0; i < data.length; i++) {
            if (data[i] == ch) {
                //找到,返回对应下标
                return i;
            }
        }
        //没有找到
        return -1;
    }

    //显示图的邻接矩阵
    public void showGraph () {

        for (int[] link : weight) {
            System.out.println(Arrays.toString(link));
        }
    }

}
