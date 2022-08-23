package com.w.tree;

public class ArrayBinaryTreeDemo {
    
    public static void main(String[] args) {
        
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        System.out.println("前序遍历");
        arrayBinaryTree.preOrder();

        System.out.println("中序遍历");
        arrayBinaryTree.infixOrder();

        System.out.println("后序遍历");
        arrayBinaryTree.postOrder();
    }
}

//实现顺序存储二叉树遍历
class ArrayBinaryTree {

    //存储数据节点的数组
    private int[] array;

    //构造器
    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    //重载前序遍历的方法
    public void preOrder() {

        preOrder(0);
    }

    //实现顺序存储二叉树的前序遍历
    public void preOrder(int index) {

        //判断数组是否为null
        if (array == null || array.length == 0) {
            System.out.println("数组为null");
            return;
        }

        //输出当前节点
        System.out.println(array[index]);

        //遍历左子树
        //判断索引是否越界
        if (2 * index + 1 < array.length) {
            preOrder(2 * index + 1);
        }

        //遍历右子树
        //判断索引是否越界
        if (2 * index + 2 < array.length) {
            preOrder(2 * index + 2);
        }
    }

    //重载中序遍历
    public void infixOrder() {

        infixOrder(0);
    }

    //实现中序遍历
    public void infixOrder(int index) {

        //判断数组是否为null
        if (array == null || array.length == 0) {
            System.out.println("数组为null");
            return;
        }

        //遍历左子树
        //判断索引是否越界
        if (2 * index + 1 < array.length) {
            infixOrder(2 * index + 1);
        }

        //输出当前节点
        System.out.println(array[index]);

        //遍历右子树
        //判断索引是否越界
        if (2 * index + 2 < array.length) {
            infixOrder(2 * index + 2);
        }
    }

    //重载后序遍历
    public void postOrder() {

        postOrder(0);
    }

    //实现后序遍历
    public void postOrder(int index) {

        //判断数组是否为null
        if (array == null || array.length == 0) {
            System.out.println("数组为null");
            return;
        }

        //遍历左子树
        //判断索引是否越界
        if (2 * index + 1 < array.length) {
            postOrder(2 * index + 1);
        }

        //遍历右子树
        //判断索引是否越界
        if (2 * index + 2 < array.length) {
            postOrder(2 * index + 2);
        }
        
        //输出当前节点
        System.out.println(array[index]);
    }

}

