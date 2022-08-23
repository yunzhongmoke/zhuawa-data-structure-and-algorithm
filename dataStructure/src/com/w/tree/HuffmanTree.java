package com.w.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

    public static void main(String[] args) {
        
        int array[] = {13, 7, 8, 3, 29, 6, 1};
        //测试huffman tree
        HuffmanTreeNode root = createHuffmanTree(array);
        //遍历
        preOrder(root);
    }

    //前序遍历
    public static void preOrder(HuffmanTreeNode root) {

        if (root == null) {
            //根节点为null，不能遍历
            return;
        } else {
            root.preOrder(root);
        }
    }

    //创建HuffmanTree
    public static HuffmanTreeNode createHuffmanTree(int[] array) {

        //创建一个集合
        List<HuffmanTreeNode> nodes = new ArrayList<HuffmanTreeNode>(); 

        //将数组中值加入至HuffmanTreeNode中的value中，并将其添加至集合中
        for (int value : array) {
            HuffmanTreeNode huffmanTreeNode = new HuffmanTreeNode(value);
            nodes.add(huffmanTreeNode);
        }
        
        while (nodes.size() > 1) {
            //对集合中的数字按value进行排序，从小到大
            Collections.sort(nodes);

            //取出前两个最小的权值节点
            HuffmanTreeNode leftNode = nodes.get(0);
            HuffmanTreeNode rightNode = nodes.get(1);

            //构建一个新的二叉树
            HuffmanTreeNode parentNode = new HuffmanTreeNode(leftNode.getValue() + rightNode.getValue());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);

            //删除前两个最小的权值
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将parentNode加入到nodes中
            nodes.add(parentNode);
        }
        //返回最终形成的huffman tree的根节点
        return nodes.get(0);
    }

    //快速排序
    public static void quickSort(int[] array, int left, int right) {

        //左下标
        int l = left;
        //右下标
        int r = right;
        //中轴值
        int pivot = array[(l + r) / 2];
        //定义临时变量，用于值交换
        int temp = 0;
        while (l < r) {
            //从左边找比下标pivot大的值
            while (array[l] < pivot) {
                l++;
            }
            //从右边找下标pivot小的值
            while (array[r] > pivot) {
                r--;
            }
            //如果左边全部都是小于等于下标pivot的值，或者右边全部都是大于等于下标pivot的值，不交换值
            if (l >= r) {
                //防止死龟
                l++;
                r--;
                break;
            }
            
            //值交换
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            //向右递归时，right的索引为right - 1
            if (array[l] == pivot) {
                r--;
            }

            //向左递归时，left的索引为left + 1
            if (array[r] == pivot) {
                l++;
            }

        }

        //向左递归
        if (left < r) {
            quickSort(array, left, r);
        }

        //向右递归
        if (l < right) {
            quickSort(array, l, right);
        }
    }

}

class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {

    private int value;//节点权值
    private HuffmanTreeNode left;//指向左子节点
    private HuffmanTreeNode right;//指向右子节点

    public HuffmanTreeNode(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HuffmanTreeNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanTreeNode left) {
        this.left = left;
    }

    public HuffmanTreeNode getRight() {
        return right;
    }

    public void setRight(HuffmanTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HuffmanTreeNode [value=" + value + "]";
    }

    @Override
    public int compareTo(HuffmanTreeNode o) {
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder(HuffmanTreeNode node) {

        if (node != null) {
            System.out.println(node);
            node.preOrder(node.left);
            node.preOrder(node.right);
        }
    }

}