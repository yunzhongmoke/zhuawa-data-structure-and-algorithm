package com.w.search;

import java.util.Arrays;

public class InterpolationSearch {
    
    public static void main(String[] args) {
        
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        int result = interpolationSearch(array, 0, array.length - 1, 50);
        System.out.println("result = " + result);
    }

    //插值查找
    public static int interpolationSearch(int[] array, int left, int right, int findValue) {

        System.out.println("查找次数~");

        //没有找到，退出递归
        if (left > right || findValue < array[left] || findValue > array[right]) {
            return -1;
        }

        //自适应中值
        int mid = left + (right - left) * (findValue - array[left]) / (array[right] - array[left]);
        if (findValue > array[mid]) {
            //向右递归
            return interpolationSearch(array, mid + 1, right, findValue);
        } else if (findValue < array[mid]) {
            //向左递归
            return interpolationSearch(array, left, mid - 1, findValue);
        } else {
            //找到
            return mid;
        }
    }
}
