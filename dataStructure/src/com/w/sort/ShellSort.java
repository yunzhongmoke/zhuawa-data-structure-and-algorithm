package com.w.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    
    public static void main(String[] args) {
        
        // int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        // shellSort2(array);

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date1Str = format.format(date1);
        System.out.println(date1Str);

        long startTime = System.currentTimeMillis();
        shellSort2(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + (endTime - startTime));
        
        Date date2 = new Date();
        String date2Str = format.format(date2);
        System.out.println(date2Str);
    }

    public static void shellSort(int[] array) {

        //交换法
        int temp = 0;
        int count = 0;//用于记录当前轮数
        for (int group = array.length / 2; group >= 1; group /= 2) {

            for (int i = 0; i < array.length - group; i++) {
 
                for (int j = i; j >= 0; j -= group) {

                    if (array[j] > array[j + group]) {
                        temp = array[j];
                        array[j] = array[j + group];
                        array[j + group] = temp;
                    }
                }
            }
            // System.out.println("第" + (++count) + "轮结果是：" + Arrays.toString(array));
        }
    }

    public static void shellSort2(int[] array) {

        //移位法
        int temp = 0;
        int count = 0;//用于记录当前轮数
        for (int group = array.length / 2; group >= 1; group /= 2) {

            for (int i = 0; i < array.length - group; i++) {

                int j = i + group;
                temp = array[j];
                while (j >= group && temp < array[j - group]) {
                    array[j] = array[j - group];
                    j -= group;
                }
                array[j] = temp;
            }
            // System.out.println("第" + (++count) + "轮结果是：" + Arrays.toString(array));
        }
    }
}
