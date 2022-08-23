package com.w.dijkstra;

import java.util.Arrays;
import java.util.LinkedList;

public class DijkstraAlgorithm {
    
    public static void main(String[] args) {
        
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		//邻接矩阵
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = 65535;// 表示不可以连接
		matrix[0]=new int[]{N,5,7,N,N,N,2};  
        matrix[1]=new int[]{5,N,N,9,N,N,3};  
        matrix[2]=new int[]{7,N,N,N,8,N,N};  
        matrix[3]=new int[]{N,9,N,N,N,4,N};  
        matrix[4]=new int[]{N,N,8,N,N,5,4};  
        matrix[5]=new int[]{N,N,N,4,5,N,6};  
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        Graph graph = new Graph(vertex, matrix);
        graph.dijkstra(6);
        graph.vv.show();
    }
}

class Graph {

    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    VisitedVertex vv;//已访问顶点的集合

    //构造器
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }
    
    //显示图
    public void showGraph() {

        //遍历邻接矩阵
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    //dijkstra算法实现
    /**
     * 
     * @param index 表示原始出发顶点的下标
     */
    public void dijkstra (int index) {

        //初始化vv
        vv = new VisitedVertex(vertex.length, index);
        //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
        update(index);
        //广度优先遍历余下的顶点(顶点个数为总顶点个数-1)
        for (int i = 1; i < vertex.length; i++) {
            //选择新的出发顶点
            index = vv.updateStartVertex();
            //更新新的出发顶点到周围顶点的距离和周围顶点的前驱顶点
            update(index);
        }
    }


    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    public void update (int index) {

        int length = 0;
        //遍历邻接矩阵matrix[index]的行，
        for (int i = 0; i < matrix[index].length; i++) {
            //length表示原始出发顶点到以index为出发顶点所到达顶点距离的和
            length = vv.getDistance(index) + matrix[index][i];
            //比较length的大小
            if (length < vv.getDistance(i)) {
                //更新原始出发顶点到i顶点的距离
                vv.updateDistance(i, length);
                //更新i的前驱顶点
                vv.updatePrecursorVertex(i, index);
            } 
        }
    }
}

//已访问的顶点集合
class VisitedVertex {

    //记录各个顶点是否被访问
    int[] already_arr;
    //记录各个顶点下标对应的前一个顶点下标
    int[] pre_visited;
    //记录原始出发顶点到其他顶点的最短距离
    int[] minimumDistance;
    //创建一个linkedList数组，用于保存待出发的顶点集合
    LinkedList<Integer> linkedList = new LinkedList<Integer>();

    //构造器
    /**
     * @param length 顶点的个数
     * @param index 出发顶点对应的下标
     */
    public VisitedVertex (int length, int index) {

        already_arr = new int[length];
        pre_visited = new int[length];
        minimumDistance = new int[length];
        //初始化minimumDistance数组
        Arrays.fill(minimumDistance, 65535);
        //将出发顶点标记为已访问
        already_arr[index] = 1;
        //设置原始出发顶点的访问距离为0
        minimumDistance[index] = 0;
    }

    //判断顶点是否被访问
    public boolean isVisited (int index) {

        return already_arr[index] == 1;
    }

    //获取原始出发顶点到该顶点的距离
    public int getDistance (int index) {

        return minimumDistance[index];
    }

    //更新该顶点到原始出发顶点的距离
    public void updateDistance (int index, int distance) {

        minimumDistance[index] = distance;
    }

    //更新该顶点的前驱顶点
    public void updatePrecursorVertex (int index, int precursorVertex) {

        pre_visited[index] = precursorVertex;
    }

    //继续选择返回新的出发顶点，根据广度优先遍历
    public int updateStartVertex () {

        int min = 65535;//表示两个顶点间未连通的距离
        //遍历already_arr数组
        for (int i = 0; i < already_arr.length; i++) {
            //判断该顶点是否被访问，以及两个顶点间是否连通
            if (!isVisited(i) && getDistance(i) < min) {
                linkedList.addLast(i);
                //将其设置为已访问
                already_arr[i] = 1;
            }
        }
        return linkedList.removeFirst();
    }

    //显示最后的结果
	//即将三个数组的情况输出
	public void show() {
		
		System.out.println("==========================");
		//输出already_arr
		for(int i : already_arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		//输出pre_visited
		for(int i : pre_visited) {
			System.out.print(i + " ");
		}
		System.out.println();
		//输出dis
		for(int i : minimumDistance) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		//为了好看最后的最短距离，我们处理
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int count = 0;
		for (int i : minimumDistance) {
			if (i != 65535) {
				System.out.print(vertex[count++] + "("+i+") ");
			} else {
				System.out.println("N ");
			}
		}
		System.out.println();
		
	}
}