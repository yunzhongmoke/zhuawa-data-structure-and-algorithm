package com.w.stack;

public class Calculator {
    
    public static void main(String[] args) {
        
        String expression = "900-7-1";
        //数字栈
        ArrayStack2 numStack = new ArrayStack2(10);
        //符号栈
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到char保存到ch
        String keepNum = "";//用于拼接多位数
        //扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch
            if (ArrayStack2.isOper(ch)) {//是操作符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {//不为空
                    //判断优先级
                    if (ArrayStack2.priority(oper) <= ArrayStack2.priority(operStack.peek())) {
                        //当前优先级低于栈内优先级
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = ArrayStack2.calculator(num1, num2, oper);
                        //将运算结果推入栈
                        numStack.push(res);
                    }
                } 
                //当前运算符入符号栈
                operStack.push(ch);
            } else {//是数字
                //处理多位数
                keepNum += ch;
                //如果ch是expression的最后一位，直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字
                    if (ArrayStack2.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //是操作符
                        numStack.push(Integer.parseInt(keepNum));
                        //将keepNum置空
                        keepNum = "";
                    }
                }
            }
            //让index++，并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //扫描完毕，运算栈空的数据
        while (true) {
            //如果符号栈为空，存于数值栈中的数值就是结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = ArrayStack2.calculator(num1, num2, oper);
            //将结果存入栈中
            numStack.push(res);
        }
        System.out.printf("%s = %d", expression, numStack.pop());
    }
}

class ArrayStack2 {

    private int maxSize;//栈的大小
    private int[] stack;//数组，模拟栈
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {

        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断是否栈满
    public boolean isFull() {

        return top == maxSize - 1;
    }

    //判断是否栈空
    public boolean isEmpty() {

        return top == -1;
    }

    //增加一个方法，可以返回当前栈顶的值，不是出栈
    public int peek() {

        return stack[top];
    }

    //入栈-push
    public void push(int value) {
        
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop
    public int pop() {

        //先判断栈是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况[遍历栈]，遍历时，需要从栈顶开始显示数据
    public void list() {

        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //判断操作符的优先级
    public static int priority(int oper) {

        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前的表达式只有 +,-,*,/
        }
    }

    //判断是不是一个运算符
    public static boolean isOper(char value) {

        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    //计算方法
    public static int calculator(int num1, int num2, int oper) {

        int res = 0;//用于存放计算的结果
        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
