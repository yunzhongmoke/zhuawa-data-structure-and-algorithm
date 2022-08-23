package com.w.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {
    
    public static void main(String[] args) {
        
        // int[] array = {101, 34, 119, 1};
        // System.out.println("排序前：");
        // System.out.println(Arrays.toString(array));

        // insertSort(array);

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
        insertSort(arr);
        Date date2 = new Date();
        String date2Str = format.format(date2);
        System.out.println(date2Str);
    }

    //插入排序
    public static void insertSort(int[] array) {

        for (int i  = 1; i < array.length; i++) {
            //待插入的值
            int insertValue = array[i];
            //指向待插入元素索引
            int insertIndex = i;

            /**
             * insertIndex >= 1 防止数组越界
             * insertValue < array[insertIndex] 排序规则
             */
            while (insertIndex >= 1 && insertValue < array[insertIndex - 1]) {
                //数据后移
                array[insertIndex] = array[insertIndex - 1];
                //索引前移
                insertIndex--;
            }
            //放入待插入的数据
            array[insertIndex] = insertValue;
            // System.out.println("第" + i + "轮的结果：" + Arrays.toString(array));

        }
    }
}
