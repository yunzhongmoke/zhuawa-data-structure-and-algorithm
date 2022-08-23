package com.w.dynamicprogramming;

public class KnapsackProblem {
    
    public static void main(String[] args) {
        
        //物品的重量
        int[] weight = {1, 4, 3};
        //物品的价值
        int[] value = {1500, 3000, 2000};
        //背包的容量
        int capacity = 4;
        //物品的个数
        int number = value.length;

        //创建二维数组
        //valueOfGoods[i][j] 表示前i个物品能够装入容量为j的背包的最大价值
        int[][] valueOfGoods = new int[number + 1][capacity + 1];
        //记录商品放入背包的情况
        int[][] path = new int[number + 1][capacity + 1];

        //初始化第一行和第一列(可以不处理，数组默认初始化为0)
        //第一列
        for (int i = 0; i < valueOfGoods.length; i++) {
            valueOfGoods[i][0] = 0;
        } 
        //第一行
        for (int i = 0; i < valueOfGoods[0].length; i++) {
            valueOfGoods[0][i] = 0;
        }

        //动态规划处理
        for (int i = 1; i < valueOfGoods.length; i++) {//从第一列开始
            for (int j = 1; j < valueOfGoods[i].length; j++) {//从第一行开始
                if (weight[i - 1] > j) {
                    //物品容量大于背包容量
                    //直接使用上一单元格装入策略
                    valueOfGoods[i][j] = valueOfGoods[i - 1][j];
                } else {
                    //物品容量小于背包容量
                    //比较装入策略
                    //valueOfGoods[i - 1][j] 上一单元格的策略
                    //value[i](当前商品的价值) + valueOfGoods[i - 1][j - weight[i - 1]](背包剩余容量的最大价值)
                    if (valueOfGoods[i - 1][j] >= value[i - 1] + valueOfGoods[i - 1][j - weight[i - 1]]) {
                        valueOfGoods[i][j] = valueOfGoods[i - 1][j];
                    } else {
                        //记录当前情况到path数组
                        path[i][j] = 1;
                        valueOfGoods[i][j] = value[i - 1] + valueOfGoods[i - 1][j - weight[i - 1]];
                    }
                }
            }
        }

        //查看路径
        int row = path.length - 1;//行的最大下标
        int column = path[0].length - 1;//列的最大下标
        //从path末尾开始寻找
        while (row > 0 && column > 0) {
            if (path[row][column] == 1) {
                System.out.println("第" + row + "个商品放入背包");
                column -= weight[row - 1];
            }
            row--;
        }

        //遍历valueOfGoods数组
        for (int i = 0; i < valueOfGoods.length; i++) {
            for (int j = 0; j < valueOfGoods[i].length; j++) {
                System.out.print(valueOfGoods[i][j] + " ");
            }
            System.out.println();
        }
    }
}
