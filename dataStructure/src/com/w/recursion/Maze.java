package com.w.recursion;

public class Maze {
    
    public static void main(String[] args) {
        
        //创建一个二维数组,表示地图
        int[][] map = new int[8][7];
        //赋值为1表示墙
        //左右置为1
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][map[0].length - 1] = 1;
        }
        //上下置为1
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[map.length - 1][i] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //打印地图
        System.out.println("地图的情况：");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //设置起点
        setWay(map, 1, 1);
        //打印之后的地图
        System.out.println("小球走后的地图："); 
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    
    /**
     * 使用递归回溯来给小球找路
     * 制定规则 下 -> 右 -> 上 -> 左
     * @param map 表示地图
     * @param i 位置
     * @param j 位置
     * @return 找到就返回true，反之false
     */
    public static boolean setWay(int[][] map, int i, int j) {

        if (map[6][5] == 2) {//通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {//当前节点没有走过
                //按指定策略走
                map[i][j] = 2;//假定该点可以走通
                if (setWay(map, i + 1, j)) {//下
                    return true;
                } else if (setWay(map, i, j + 1)) {//右
                    return true;
                } else if (setWay(map, i - 1, j)) {//上
                    return true;
                } else if (setWay(map, i, j - 1)) {//左
                    return true;
                } else {
                    //说明该点不可行，思路
                    map[i][j] = 3;
                }
            } else {
                //该点不为零，说明已经走过，或者是墙，或者不可行
                return false;
            }
        }
        return false;
    }
}