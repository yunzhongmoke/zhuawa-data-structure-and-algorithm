package com.w.tree;

import java.util.Stack;

public class ThreadedBinaryTreeDemo {
    
    public static void main(String[] args) {
        
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(6);
        Node node4 = new Node(8);
        Node node5 = new Node(10);
        Node node6 = new Node(14);

        //手动构建二叉树
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(node1);
        //测试中序线索化
        // threadedBinaryTree.infixThreadedNodes();
        //测试no值为10的节点
        // System.out.println(node5.getLeft());
        // System.out.println(node5.getRight());
        
        // threadedBinaryTree.infixList();

        //测试前序线索化
        // threadedBinaryTree.preThreadedNodes(node1);
        // threadedBinaryTree.preList();

        //测试后序线索化
        threadedBinaryTree.postThreadedNodes(node1);
        threadedBinaryTree.postList();
    }
}

class ThreadedBinaryTree {

    //根节点
    private Node root;
    private Node pre;//指向前一个节点,默认为null

    public void setRoot(Node root) {
        this.root = root;
    }


    //后序遍历线索化二叉树
    public void postList() {

        //创建一个栈
        Stack<Node> stack = new Stack<>();
        //定义一个变量，存储当前节点
        Node node = root;
        while (node != null) {
            //入栈
            stack.push(node);
            node = node.getRight();
        }
        //出栈
        while (stack.size() != 0) {
            System.out.println(stack.pop());
        }
    }

    //后序线索化二叉树
    //右指针指向前驱节点
    public void postThreadedNodes(Node node) {

        if (node == null) {
            //不能线索化
            return;
        }

        //线索化左子树
        postThreadedNodes(node.getLeft());

        //线索化右子树
        postThreadedNodes(node.getRight());

        //线索化当前节点
        if (node.getRight() == null) {
            node.setRight(pre);
        }
        //前驱节点后移
        pre = node;

    }

    //前序遍历线索化二叉树
    public void preList() {

        //定义一个变量，存储当前节点
        Node node = root;
        while (node != null) {
            System.out.println(node);
            node = node.getLeft();
        }
    }

    //前序线索化二叉树
    //左指针指向后继节点
    public void preThreadedNodes(Node node) {

        if (node == null) {
            //不能线索化
            return;
        }

        //线索化当前节点
        //处理前驱结点的后继节点
        if (pre != null && pre.getLeft() == null) {
            pre.setLeft(node);
        }
        //前驱节点后移
        pre = node;

        //线索化左子树
        preThreadedNodes(node.getLeft());

        //线索化右子树
        preThreadedNodes(node.getRight());
    }

    //中序遍历线索化二叉树
    public void infixList() {

        //定义一个变量，存储当前遍历节点
        Node node = root;
        while (node != null) {
            //循环找到leftType == 1的节点，即当前子树中序遍历头节点(node为根节点)
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //让node指向后继节点，并打印
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //指向右子树
            node = node.getRight();
        }
    }
    
    //重载中序线索化二叉树
    public void infixThreadedNodes() {

        infixThreadedNodes(root);
    }

    //中序线索化二叉树
    public void infixThreadedNodes(Node node) {

        //判断node是否为null
        if (node == null) {
            //不能线索化
            return;
        }

        //线索化左子树
        infixThreadedNodes(node.getLeft());

        //线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型,指向前驱节点
            node.setLeftType(1);
        }
        //处理前驱节点的后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向后继节点
            pre.setRight(node);
            //修改前驱节点的右指针类型,指向后继节点
            pre.setRightType(1);
        }
        //前驱节点后移,指向当前节点
        pre = node;

        //线索化右子树
        infixThreadedNodes(node.getRight());
    }
}

class Node {

    private int no;
    private Node left;
    private Node right;

    //中序遍历：
    //1. 如果 leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点 
    //2. 如果 rightType == 0 表示指向是右子树, 如果 1 表示指向后继结点 
    private int leftType; 
    private int rightType;

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "Node [no=" + no + "]";
    }
    
}
