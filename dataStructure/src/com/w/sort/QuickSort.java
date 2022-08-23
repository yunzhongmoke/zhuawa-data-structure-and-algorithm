package com.w.sort;

import java.util.Arrays;

public class QuickSort {
    
    public static void main(String[] args) {
        
        int[] array = {-9, 78, 0, 23, -567, 70, -3, 100}; 
        quickSort(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));

        // int[] arr = new int[80000];
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i] = (int) (Math.random() * 8000000);
        // }

        // long startTime = System.currentTimeMillis();
        // quickSort(arr, 0, arr.length - 1);
        // long endTime = System.currentTimeMillis();
        // System.out.println("运行时间：" + (endTime - startTime));
    }

    public static void quickSort(int[] array, int left, int right) {

        //左下标
        int l = left;
        //右下标
        int r = right;
        //中轴值
        int pivot = array[(left + right) / 2];
        int temp = 0;//临时变量，用于存储中间值
        while (l < r) {
            //从左边找比pivot大于等于的值
            while (array[l] < pivot) {
                l++;
            }
            //从右边找比pivot小于等于的值
            while (array[r] > pivot) {
                r--;
            }

            //左边全部是小于等于 pivot 值，右边全部是大于等于 pivot 值
            if (l >= r) {
                break;
            }

            //交换值
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            //向右递归时，right的索引为right - 1
            if (array[l] == pivot) {
                r--;
            }

            //向左递归时，left的索引为left + 1
            if (array[r] == pivot) {
                l++;
            }

        }

        //防止出现死龟
        if (l == r) {
            l++;
            r--;
        }

        //向左递归
        if (left < r) {
            quickSort(array, left, r);
        }

        //向右递归
        if (right > l) {
            quickSort(array, l, right);
        }
    }
}
