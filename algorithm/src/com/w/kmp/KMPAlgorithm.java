package com.w.kmp;

public class KMPAlgorithm {
    
    public static void main(String[] args) {
        
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int index = kmpSearch(str1, str2);
        System.out.println(index);
    }

    //kmp搜索算法
    public static int kmpSearch(String str1, String str2) {

        //获取子串的匹配表
        int[] next = kmpNext(str2);
        //遍历str1
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //比较值
            //不相等
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                //j回溯
                j = next[j - 1];
            }
            //相等
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            //判断是否找到
            if (j == str2.length()) {
                //找到，因为j代表的个数，不是索引
                return i - (j - 1);
            }
        }
        //没有找到
        return -1;
    }

    //获取一个字符串及其子串的部分匹配表
    public static int[] kmpNext(String str) {

        //创建一个next数组保存部分匹配值
        int[] next = new int[str.length()];
        //字符串长度为1时，部分匹配值为0
        next[0] = 0;
        //初始化next数组
        for (int i = 1, j = 0; i < next.length; i++) {//i表示next数组索引，j表示匹配值
            //首尾值判断
            //值不相等
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                //j回溯，根据next匹配表
                j = next[j - 1];
            }
            //值相等
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            //赋值
            next[i] = j;
        }
        //返回next数组
        return next;
    }
}
