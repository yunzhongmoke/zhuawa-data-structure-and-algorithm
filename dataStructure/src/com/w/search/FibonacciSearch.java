package com.w.search;

public class FibonacciSearch {
    
    public static void main(String[] args) {
        
        int [] array = {1, 8, 10, 89, 1000, 1234};

        //{1, 8, 10, 89, 1000, 1234, 1234, 1234};
        int result = fibonacciSearch(array, 1234);
        System.out.println("result = " + result);
    }

    //构建一个斐波那契数组
    public static int[] fibonacci() {

        int[] array = new int[20];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i < array.length; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array;
    }

    //斐波那契查找算法
    public static int fibonacciSearch(int[] array, int findValue) {

        int low = 0;
        int high = array.length - 1;
        int mid = 0;

        //获取一个Fibonacci数组
        int[] fibonacci = fibonacci();

        //获取到斐波那契分割数组的下标
        int k = 0;
        while (high > fibonacci[k] - 1) {
            k++;
        }

        //定义一个数组，对原数组进行扩容
        int[] temp = new int[fibonacci[k]];
        for (int i = 0; i < temp.length; i++) {
            //不足部分用末尾索引位值填充
            if (i > array.length - 1) {
                temp[i] = array[array.length - 1];
                continue;
            }
            temp[i] = array[i];
        }

        while (low <= high) {
            mid = low + fibonacci[k - 1] - 1;
            if (findValue > temp[mid]) {
                //向右边寻找
                low = mid + 1;
                k -= 2;
            } else if (findValue < temp[mid]) {
                //向左边寻找
                high = mid - 1;
                k--;
            } else {
                //找到了，但mid可能大于原数组末尾索引
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        //没找到
        return -1;
    }
}
