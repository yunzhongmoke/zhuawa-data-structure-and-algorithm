package com.w.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    
    public static void main(String[] args) {
        
        // int[] array = {101, 34, 119, 1};
        // System.out.println("排序前：");
        // System.out.println(Arrays.toString(array));

        // selectSort(array);

        // System.out.println("排序后：");
        // System.out.println(Arrays.toString(array));


        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date1Str = format.format(date1);
        System.out.println(date1Str);
        selectSort(arr);
        Date date2 = new Date();
        String date2Str = format.format(date2);
        System.out.println(date2Str);
    }

    //选择排序
    public static void selectSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            //假定值
            int minIndex = i;
            int min = array[minIndex];

            for (int j = i + 1; j < array.length; j++) {
                //排序规则(从小到大)
                if (min > array[j]) {//假定最小值并不是最小
                    minIndex = j;//重置minIndex
                    min = array[minIndex];//重置min
                }
            }
            //是否与预估值相匹配
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
            // System.out.println("第" + (i + 1) + "轮排序结果：" + Arrays.toString(array));
        }
    }

}
