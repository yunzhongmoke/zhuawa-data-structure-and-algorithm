package com.w.recursion;

public class EightQueens {
    
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如arr= {0, 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    //记录正确结果的次数
    int count = 0;
    //记录冲突的次数
    int judgeCount = 0;

    public static void main(String[] args) {
    
        EightQueens eightQueens = new EightQueens();
        eightQueens.check(0);
        System.out.println("count = " + eightQueens.count);
        System.out.println("judgeCount = " + eightQueens.judgeCount);
    }

    /**
     * 从第n + 1个皇后开始放
     * @param n 表示第n + 1个皇后
     */
    public void check(int n) {

        if (n == max) {
            //八个皇后已经放好
            //打印
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < array.length; i++) {
            //先把当前这个皇后n，放到该行的第i + 1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) {
                //不冲突
                //接着放第n + 1个皇后，开始递归
                check(n + 1);
            }

            //如果冲突，就继续执行 array[n] = i; 即将第 n 个皇后，放置在本行的后移的一个位置
        }
    }

    /**
     * 放置第n + 1个皇后，检测是否与前面的放置的冲突
     * @param n 表示第n + 1个皇后
     * @return
     */
    public boolean judge(int n) {

        //统计次数
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(i -n) == Math.abs(array[i] - array[n])) {//不满足条件
                return false;
            }
        }
        return true;
    }

    //打印皇后所处的位置
    public void print() {

        //统计次数
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
