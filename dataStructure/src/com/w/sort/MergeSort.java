package com.w.sort;

import java.util.Arrays;

public class MergeSort {
    
    public static void main(String[] args) {
        
        // int[] array = {8, 4, 5, 7, 1, 3, 6, 2};
        // int[] temp = new int[array.length];
        // mergeSort(array, 0, array.length - 1, temp);
        // System.out.println(Arrays.toString(array));

        int[] arr = new int[80000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        
        long startTime = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, temp);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + (endTime - startTime));
    }

    //分 + 合
    public static void mergeSort(int[] array, int left, int right, int temp[]) {

        //中间索引
        int mid = (left + right) / 2;
        if (left < right) {
            //左递归分解
            mergeSort(array, left, mid, temp);
            //右递归分解
            mergeSort(array, mid + 1, right, temp);
            //合并
            merge(array, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法
     * @param array 排序的原始数组
     * @param left 左边有序数组的初始索引
     * @param mid 中间索引
     * @param right 右边有序数组的结尾索引 
     * @param temp 用于存储中间值的临时数组
     */
    public static void merge(int[] array, int left, int mid, int right, int[] temp) {

        //左边有序数组的初始索引
        int l = left;
        //右边有序数组的初始索引
        int r = mid + 1;
        //指向临时数组的当前索引
        int t = 0;

        //先把左右两边(有序)的数据按照规则填充到 temp 数组 
        //直到左右两边的有序序列，有一边处理完毕为止
        while (l <= mid && r <= right) {
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素 
            //即将左边的当前元素，填充到 temp 数组
            if (array[l] < array[r]) {
                temp[t] = array[l];
                l++;
            } else {
                temp[t] = array[r];
                r++;
            }
            t++;
        }

        //把有剩余数据的一边的数据依次全部填充到 temp
        //左边有序数组有剩余数据
        while (l <= mid) {
            temp[t] = array[l];
            l++;
            t++;
        }
        //右边有序数组有剩余数据
        while (r <= right) {
            temp[t] = array[r];
            r++;
            t++;
        }

        // System.out.println(left + " " + right);
        //将temp数组中的元素拷贝到原始数组中(temp不一定存有array的全部数据)
        //将temp索引重置
        t = 0;
        //定义一个临时变量，用于遍历合并后的数据
        int tempLeft = left;
        while (tempLeft <= right) {
            array[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}