package com.w.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
 
    public static void main(String[] args) {
        
        int[] array = {6, 9, 3, 2, 5};

        // int[] arr = new int[80000];
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i] = (int) (Math.random() * 8000000);
        // }

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date1Str = format.format(date1);
        System.out.println(date1Str);
        bubbleSort(array);
        // bubbleSort(arr);
        Date date2 = new Date();
        String date2Str = format.format(date2);
        System.out.println(date2Str);

    }

    //冒泡排序
    public static void bubbleSort(int[] array) {

        //优化冒泡排序
        boolean flag = false;
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            //重置flag
            flag = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                //按照从小到大的顺序进行交换
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

            }
            //判断数据是否发生交换(没有发生交换，说明已经是一个有序数组)
            if (!flag) {
                break;
            }
            System.out.println("第" + (i + 1) + "轮排序后：" + Arrays.toString(array)) ;
        }
    }
}
