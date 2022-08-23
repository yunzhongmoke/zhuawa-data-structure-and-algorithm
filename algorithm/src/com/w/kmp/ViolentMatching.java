package com.w.kmp;

public class ViolentMatching {
    
    public static void main(String[] args) {
        
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violentMatching(str1, str2);
        System.out.println(index);
    }

    //暴力匹配
    public static int violentMatching(String str1, String str2) {

        //将字符串转化为字符数组
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        //s1字符数组长度
        int s1Length = s1.length;
        //s2字符数组长度
        int s2Length = s2.length;

        //索引指向s1
        int i = 0;
        //索引指向s2
        int j = 0;
        while (i < s1Length && j < s2Length) {
            //单字符匹配
            if (s1[i] == s2[j]) {
                //匹配成功
                i++;
                j++;
            } else {
                //匹配失败
                i = i - (j - 1);
                j = 0;
            }
        }
        //判断是否匹配成功
        if (j == s2Length) {
            //匹配成功
            return i - j;
        }
        //匹配失败
        return -1;
    }
}
