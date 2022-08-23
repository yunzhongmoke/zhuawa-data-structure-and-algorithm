package com.w.tree;

import java.util.Arrays;

public class HeapSort {
    
    public static void main(String[] args) {
        
        // int[] array = {4, 6, 8, 5, 9};
        // heapSort(array);

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long startTime = System.currentTimeMillis();
        heapSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + (endTime - startTime));
    }

    //实现堆排序
    public static void heapSort(int[] array) {

        //将无序序列构建成一个大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        // System.out.println(Arrays.toString(array));

        //排序，将根节点(即最大值)放置末尾
        //定义一个临时变量,用于交换
        int temp = 0;
        for (int i = array.length - 1; i > 0; i--) {
            //根节点与末尾节点交换
            temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            //重新调整节点-1的二叉树
            adjustHeap(array, 0, i);
        }

        // System.out.println("排序后");
        // System.out.println(Arrays.toString(array));
    } 

    //将一个数组(二叉树)，调整成一个大顶堆
    /**
     * @param array 需调整的数组
     * @param index 含有子节点的节点
     * @param length 数组需调整的长度
     */
    public static void adjustHeap(int[] array, int index, int length) {

        //保存当前节点的值
        int temp = array[index];
        //i * 2 + 1是i的右子节点
        for (int i = 2 * index + 1; i < length; i = i * 2 + 1) {
            //比较左右子节点的大小
            if (i + 1 < length && array[i] < array[i + 1]) {
                //右子节点大于左子节点,i右移至右子节点
                i++;
            }
            //当前节点与子节点比较
            if (array[i] > temp) {
                //子节点大于当前节点值
                //将其赋值给当前节点
                array[index] = array[i];
                //index指向i，继续循环比较
                index = i;
            } else {
                //从下至上有序，当前节点比子节点大就不需要再比较
                return;
            }
        }
        //将子节点值赋值给当前节点，完成交换
        array[index] = temp;
    }
}
