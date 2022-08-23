package com.w.avl;

public class AVLTreeDemo {
    
    public static void main(String[] args) {
        
        // int[] array = {4, 3, 6, 5, 7, 8};
        // int[] array = {10, 12, 8, 9, 7, 6};
        // int[] array = {10, 11, 7, 6, 8, 9};
        int[] array = {11, 10, 9, 8, 7};
        //创建AVLTree
        AVLTree avlTree = new AVLTree();
        //遍历数组，将数据加入至二叉排序数
        for (int i = 0; i < array.length; i++) {
            avlTree.addNode(new Node(array[i]));
        }
        //中序遍历
        avlTree.infixOrder(avlTree.getRoot());
        //
        System.out.println("height = " + avlTree.height(avlTree.getRoot().left));
        System.out.println("height = " + avlTree.height(avlTree.getRoot().right));
        System.out.println(avlTree.getRoot().left);
    }
}

//创建AVLTree
class AVLTree {

    //根节点
    private Node root;

    //将avl tree右旋转
    private void rightRotate(Node node) {

        Node newNode = new Node(node.value);
        newNode.right = node.right;
        newNode.left = node.left.right;
        node.value = node.left.value;
        node.left = node.left.left;
        node.right = newNode;
    }

    //将avl tree左旋转
    /**
     * 
     * @param node 需要旋转的avl tree根节点
     */
    private void leftRotate(Node node) {

        //以当前根节点的值创建新的节点
        Node newNode = new Node(node.value);
        //把新节点的左子树设置为当前节点的左子树
        newNode.left = node.left;
        //把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = node.right.left;
        //把当前节点的值换位右子节点的值
        node.value = node.right.value;
        //把当前节点的右子树设置为右子树的右子树
        node.right = node.right.right;
        //把当前节点的左子树设置为新节点
        node.left = newNode;
    }

    //计算树的高度
    public int height(Node node) {

        //判断node节点是否为null
        if (node == null) {
            return 0;
        }

        return Math.max(node.left == null ? 0 : height(node.left), 
                        node.right == null ? 0 : height(node.right)) + 1;
    }

    //中序遍历
    public void infixOrder(Node node) {
        
        //判断根节点是否为null
        if (root == null) {
            System.out.println("AVLTree为null");
            return;
        }
        //AVLTree不为null
        //判断当前节点是否为null
        if (node != null) {
            //向左递归
            infixOrder(node.left);
            //处理当前节点
            System.out.println(node);
            //向右递归
            infixOrder(node.right);
        }
    }

    //重载添加节点的方法
    public void addNode(Node node) {

        addNode(root, node);
    }

    //添加节点
    /**
     * 
     * @param currentNode 遍历的节点
     * @param node 待加入节点
     */
    public void addNode(Node currentNode, Node node) {

        //判断根节点是否为null
        if (root == null) {
            root = node;
            return;
        } 
        //正常添加
        //比较value的大小
        if (currentNode.value > node.value) {
            //判断当前节点左子节点是否为null
            if (currentNode.left == null) {
                currentNode.left = node;
            } else {
                //向左递归添加
                addNode(currentNode.left, node);
            }
        } else {
            //判断当前节点右子节点是否为null
            if (currentNode.right == null) {
                currentNode.right = node;
            } else {
                //向右递归添加
                addNode(currentNode.right, node);
            }
        }
        //旋转二叉排序树，使其成为avl tree
        //左旋转
        if ((height(currentNode.right) - height(currentNode.left)) > 1) {
            //判断当前节点的右子节点的左子树高度是否大于右子树高度
            if (height(currentNode.right.left) > height(currentNode.right.right)) {
                //右旋转
                rightRotate(currentNode.right);
            }
            leftRotate(currentNode);
            return;
        }
        //右旋转
        if ((height(currentNode.left) - height(currentNode.right)) > 1) {
            //判断当前节点的左子节点的右子树高度是否大于左子树高度
            if (height(currentNode.left.right) > height(currentNode.left.left)) {
                //左旋转
                leftRotate(currentNode.left);
            }
            rightRotate(currentNode);
            return;
        }
    
    }

    public Node getRoot() {
        return root;
    }
}

//创建Node节点
class Node {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }
}
