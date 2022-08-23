package com.w.binarysearchnorecursion;

public class BinarySearchNoRecursion {

    public static void main(String[] args) {
        
        int[] array = {1, 3, 8, 10, 11, 67, 100};
        System.out.println(binarySearch(array, 30));
    }

    //非递归实现二分查找
    /**
     * 
     * @param array 目标数组
     * @param target 需要查找的数
     * @return 返回数组下标索引，没有找到返回-1
     */
    public static int binarySearch(int[] array, int target) {

        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                //找到了
                return mid;
            } else if (target > array[mid]) {
                //向右边查找
                left = mid + 1;
            } else {
                //向左边查找
                right = mid - 1;
            }
        }
        //没有找到
        return -1;
    }
}