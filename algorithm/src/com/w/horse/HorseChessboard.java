package com.w.horse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.awt.Point;
import java.text.Collator;

public class HorseChessboard {

    public static void main(String[] args) {
        
        //测试
        X = 8;
        Y = 8;
        //设置初始位置
        int row = 1;
        int column = 1;
        //初始化visted数组和chessboard数组
        visited = new boolean[X * Y];
        chessborad = new int[X][Y];
        //测试算法
        long startTime = System.currentTimeMillis();
        traversalChessboard(row - 1, column - 1, 1);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        //遍历chessborad数组
        for (int[] item : chessborad) {
            for (int i = 0; i < item.length; i++) {
                System.out.printf("%2d ",item[i]);
            }
            System.out.println();
        }
    }
    
    //棋盘的大小
    private static int X;
    private static int Y;
    //创建一个数组，用于判断棋盘的各个位置是否被访问过
    private static boolean[] visited;
    //用于判断棋盘的各个位置是否都被访问过
    private static boolean finished;
    //记录每步所在的位置
    private static int[][] chessborad;

    //完成骑士周游问题的算法
    /**
     * 
     * @param row 出发点的横坐标
     * @param column 出发点的纵坐标
     * @param step 记录步长，初始步长为1
     */
    public static void traversalChessboard(int row, int column, int step) {

        //记录该步所在的位置
        chessborad[row][column] = step;
        //标记该位置为已访问
        visited[row * X + column] = true;
        //获取下一步的位置的集合
        ArrayList<Point> nextPoints = next(new Point(column, row));
        Collections.sort(nextPoints, (o1, o2) -> next(o1).size() - next(o2).size());
        //回溯下一个位置集合的每一个位置，直到找到正确的路线，或者集合为null
        while (!nextPoints.isEmpty()) {
            //取出下一个位置
            Point nextPoint = nextPoints.remove(0);
            //判断该位置是否被访问
            if (!visited[nextPoint.y * X + nextPoint.x]) {
                //没有被访问
                //递归下一个位置
                traversalChessboard(nextPoint.y, nextPoint.x, step + 1);
            }
        }
        //判断是否完成了任务
        if (step < X * Y && !finished) {
            //没有完成，将该个位置对应的visited[]和chessborad[]数组重置
            visited[row * X + column] = false;
            chessborad[row][column] = 0;
            return;
        }
        //完成了任务
        finished = true;
    }

    //根据当前位置，判断能够走哪些位置
    private static ArrayList<Point> next(Point currentPoint) {

        //创建一个ArrayList集合，将能够走的位置放入到集合中
        ArrayList<Point> list = new ArrayList<>();
        //创建一个Point对象，模拟走的下一个位置是否可行
        Point nextPoint = new Point();
        //模拟走的位置是否可行
        //左上角
        if ((nextPoint.x = currentPoint.x - 2) >= 0 && (nextPoint.y = currentPoint.y - 1) >= 0) {
            //加入集合中
            list.add(new Point(nextPoint));
        }
        if ((nextPoint.x = currentPoint.x - 1) >= 0 && (nextPoint.y = currentPoint.y - 2) >= 0) {
            //加入集合中
            list.add(new Point(nextPoint));
        }
        //右上角
        if ((nextPoint.x = currentPoint.x + 1) < X && (nextPoint.y = currentPoint.y - 2) >= 0) {
            //加入集合中
            list.add(new Point(nextPoint));
        }
        if ((nextPoint.x = currentPoint.x + 2) < X && (nextPoint.y = currentPoint.y - 1) >= 0) {
            //加入集合中
            list.add(new Point(nextPoint));
        }
        //右下角
        if ((nextPoint.x = currentPoint.x + 2) < X && (nextPoint.y = currentPoint.y + 1) < Y) {
            //加入集合中
            list.add(new Point(nextPoint));
        }
        if ((nextPoint.x = currentPoint.x + 1) < X && (nextPoint.y = currentPoint.y + 2) < Y) {
            //加入集合中
            list.add(new Point(nextPoint));
        }
        //左下角
        if ((nextPoint.x = currentPoint.x - 1) >= 0 && (nextPoint.y = currentPoint.y + 2) < Y) {
            //加入集合中
            list.add(new Point(nextPoint));
        }
        if ((nextPoint.x = currentPoint.x - 2) >= 0 && (nextPoint.y = currentPoint.y + 1) < Y) {
            //加入集合中
            list.add(new Point(nextPoint));
        }
        //返回集合
        return list;
    }
}
