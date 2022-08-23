package com.w.sort;

import java.util.Arrays;

public class RadixSort {
    
    public static void main(String[] args) {
        
        // int[] array = {53, 3, 542, 748, 14, 214};
        // radixSort(array);

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long startTime = System.currentTimeMillis();
        radixSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + (endTime - startTime));
    }

    //基数排序的方法
    public static void radixSort(int[] array) {

        //找出array数组中的最大值
        int max = array[0];//假设最大值为第一个元素
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        //最大值的位数
        int maxLength = (max + "").length();

        //定义一个二维数组，代表桶的数量以及桶内数据
        int[][] bucket = new int[10][array.length];

        //用于保存每个桶内的元素个数
        int[] bucketElementCounts = new int[10];
        
        //表示位数
        int n = 1;
        for (int m = 0; m < maxLength; m++) {
            //将数据按规则放入桶内
            for (int i = 0; i < array.length; i++) {
                //取出每个元素对应位的值
                int digitOfElement = array[i] / n % 10;
                //放入桶内
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[i];
                bucketElementCounts[digitOfElement]++;
            }

            //将桶内的元素放回原数组
            int index = 0;//指向原数组的初始索引
            for (int i = 0; i < bucketElementCounts.length; i++) {
                //当前桶内无数据
                if (bucketElementCounts[i] == 0) {
                    continue;
                }
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    array[index] = bucket[i][j];
                    index++;
                }
                //重置bucketElementCounts中的值
                bucketElementCounts[i] = 0;
            }
            //增加n的位数
            n *= 10;
            // System.out.println("第" + (m + 1) + "轮结果：" + Arrays.toString(array));
        } 
    }
}
