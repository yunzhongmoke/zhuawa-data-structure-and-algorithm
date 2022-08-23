package com.w.queue;

public class CircleQueue {
    
    private int maxSize; //表示数组的最大容量
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素 
    //front 的初始值 = 0
    private int front;
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定
    //rear 的初始值 = 0
    private int rear;
    private int[] arr;//该数组用于存放数据，模拟队列

    public CircleQueue(int arrMaxSize) {

        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {

        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {

        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int value) {

        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~~");
            return;
        }
        //直接将数据加入
        arr[rear] = value;
        //将rear后移，取模，防止溢出
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {

        //判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        // 这里需要分析出 front 是指向队列的第一个元素 
        // 1. 先把 front 对应的值保留到一个临时变量 
        // 2. 将 front 后移, 考虑取模 
        // 3. 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //求出当前队列有效数据的个数
    public int size() {

        return (maxSize - front + rear) % maxSize;
    }

    //显示队列的所有数据
    public void showQueue() {

        //遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }

    }

    //显示队列的头数据
    public int headQueue() {
        
        //判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }
}
