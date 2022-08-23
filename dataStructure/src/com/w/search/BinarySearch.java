package com.w.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    
    public static void main(String[] args) {
        
        int array[] = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> list = binarySearch(array, 0, array.length - 1, 0);
        System.out.println("list = " + list);
    }

    //二分查找算法
    public static List<Integer> binarySearch(int[] array, int left, int right, int findValue) {

        //中间索引
        int mid = (left + right) / 2;
        //没有找到，退出递归条件
        if (left > right) {
            return null;
        }

        if (array[mid] > findValue) {
            //在中值左边
            return binarySearch(array, left, mid - 1, findValue);
        } else if (array[mid] < findValue) {
            //在中值右边
            return binarySearch(array, mid + 1, right, findValue);
        } else {
            //找到，考虑有多个值的情况
            List<Integer> list = new ArrayList<>();
            //向mid左边扫描
            int temp = mid - 1;
            while (temp > 0 && array[temp] == findValue) {
                list.add(temp);
                temp--;
            }
            //中值是符合要求的值
            list.add(mid);
            //向mid右边扫描
            temp = mid + 1;
            while (temp < array.length - 1 && array[temp] == findValue) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
