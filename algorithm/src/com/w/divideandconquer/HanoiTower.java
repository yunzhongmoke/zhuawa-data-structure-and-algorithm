package com.w.divideandconquer;

public class HanoiTower {

    public static void main(String[] args) {
        
        hanoiTower(5, 'A', 'B', 'C');
    }

    //分治算法实现汉诺塔移动
    public static void hanoiTower(int number, char a, char b, char c) {

        if (number == 1) {
            //只有一个盘
            System.out.println("第" + number + "个盘从" + a + "=>" + c);
        } else {
            //两个及以上
            //将其看为两部分，最下层和上层
            //处理上层(a -> b)，借助c
            hanoiTower(number - 1, a, c, b);
            //处理最下层(a -> c)
            System.out.println("第" + number + "个盘从" + a + "=>" + c);
            //处理上层(b -> c)，借助a
            hanoiTower(number - 1, b, a, c);
        }
    }
}
// 第1个盘从A=>C
// 第2个盘从A=>B
// 第1个盘从C=>B
// 第3个盘从A=>C
// 第1个盘从B=>A
// 第2个盘从B=>C
// 第1个盘从A=>C
// 第4个盘从A=>B
// 第1个盘从C=>B
// 第2个盘从C=>A
// 第1个盘从B=>A
// 第3个盘从C=>B
// 第1个盘从A=>C
// 第2个盘从A=>B
// 第1个盘从C=>B
// 第5个盘从A=>C
// 第1个盘从B=>A
// 第2个盘从B=>C
// 第1个盘从A=>C
// 第3个盘从B=>A
// 第1个盘从C=>B
// 第2个盘从C=>A
// 第1个盘从B=>A
// 第4个盘从B=>C
// 第1个盘从A=>C
// 第2个盘从A=>B
// 第1个盘从C=>B
// 第3个盘从A=>C
// 第1个盘从B=>A
// 第2个盘从B=>C
// 第1个盘从A=>C