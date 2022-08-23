package com.w.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotationCalculator {
    
    public static void main(String[] args) {
        
        // String suffixExpression = "3 4 + 5 * 6 -";
        // List<String> list = getListString(suffixExpression);
        // int res = calculate(list);
        // System.out.println("结果是res = " + res);


        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        // System.out.println("infixExpressionList = " + infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        // System.out.println("suffixExpressionList = " + suffixExpressionList);
        int result = calculate(suffixExpressionList);
        System.out.println("result = " + result);
    }

    //将中缀表达式转为对应的list
    public static List<String> toInfixExpressionList(String expression) {

        //定义一个List，用于存放中缀表达式对应的内容
        List<String> list = new ArrayList<>();
        int i = 0;//指针，用于遍历中缀表达式字符串
        String str;//用于多位数拼接
        char c;//将遍历得到的字符放入到其中
        do {
            c = expression.charAt(i);
            //如果c是非数字
            if (c < 48 || c > 57) {
                list.add(c + "");
                i++;//i后移
            } else {
                //考虑多位数
                str = "";//置空
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && (c = expression.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            } 
        } while (i < expression.length());
        return list;
    }

    //将中缀表达式对应的List转为后缀表达式所对应的List
    public static List<String> parseSuffixExpressionList(List<String> list) {

        //符号栈
        Stack<String> s1 = new Stack<>();
        //用集合代替存储中间结果的栈
        List<String> s2 = new ArrayList<>();

        //遍历list
        list.forEach(item -> {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();//消除小括号"("
            } else {
                //当 item的优先级小于等于 s1 栈顶运算符, 将 s1 栈顶的运算符弹出并加入到 s2 中，再次转到(4.1)与 s1 中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //将item压入s1栈
                s1.push(item);
            }
        });
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String element : split) {
            list.add(element);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /**
     * 1)从左至右扫描，将 3 和 4 压入堆栈； 
     * 2)遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈； 
     * 3)将 5 入栈； 
     * 4)接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈； 
     * 5)将 6 入栈； 
     * 6)最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
     */
    public static int calculate(List<String> list) {
        
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历list
        list.forEach(item -> {
            //使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，然后入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(item)) {
                    res = num2 + num1;
                } else if ("-".equals(item)) {
                    res = num2 - num1;
                } else if ("*".equals(item)) {
                    res = num2 * num1;
                } else if ("/".equals(item)) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("输入的运算符有误");
                }
                //将res转为字符串推入栈
                stack.push(res + "");
            }
        });
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类 Operation 可以返回一个运算符对应的优先级
class Operation {

    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {

        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("暂不支持该运算符");
                break;
        }
        return result;
    }

}
