package com.w.binarySortTree;

public class BinarySortTreeDemo {
    
    public static void main(String[] args) {
        
        int[] array = {7, 3, 10, 12, 5, 1, 9, 2};
        //创建二叉排序树
        BinarySortTree binarySortTree = new BinarySortTree();
        //遍历数组，将数据加入至二叉排序数
        for (int i = 0; i < array.length; i++) {
            binarySortTree.addNode(new Node(array[i]));
        }
        //中序遍历
        binarySortTree.infixOrder(binarySortTree.getRoot());
        //测试删除叶子节点
        // binarySortTree.deleteNode(2);
        // binarySortTree.deleteNode(1);

        binarySortTree.deleteNode(7);
        binarySortTree.deleteNode(9);
        binarySortTree.deleteNode(10);
        binarySortTree.deleteNode(12);
        //删除后
        System.out.println("删除后~");
        binarySortTree.infixOrder(binarySortTree.getRoot());
    }
}

//二叉排序树
class BinarySortTree {

    //二叉排序树根节点
    private Node root;

    //删除节点
    public void deleteNode(int value) {

        //判断根节点是否为null
        if (root == null) {
            System.out.println("二叉树为null");
            return;
        }
        //查找待删除的节点
        Node searchDeleteNode = searchDeleteNode(value, root);
        //判断删除节点是否存在
        if (searchDeleteNode == null) {
            System.out.println("删除节点不存在~");
            return;
        }
        //查找待删除节点的父节点
        Node searchDeleteParentNode = searchDeleteParentNode(value, root);
        // //判断待删除节点是否有父节点(即是不是根节点)
        // if (searchDeleteParentNode == null) {

        // }
        //判断二叉排序树是否只有一个根节点
        if (root.left == null && root.right == null) {
            //将根节点置null
            root = null;
            return;
        }
        //如果删除的节点是叶子节点
        if (searchDeleteNode.left == null && searchDeleteNode.right == null) {
            //判断当前节点是父节点的左子节点还是右子节点
            if (searchDeleteParentNode.left != null && searchDeleteParentNode.left == searchDeleteNode) {
                //左子节点
                searchDeleteParentNode.left = null;
            } else {
                //右子节点
                searchDeleteParentNode.right = null;
            }
        } else if (searchDeleteNode.left != null && searchDeleteNode.right != null) {
            //待删除节点有两个子节点
            //待删除节点右子树的最小节点
            Node searchBinaryTreeMin = searchBinaryTreeMin(searchDeleteNode.right);
            //删除最小节点
            deleteNode(searchBinaryTreeMin.value);
            //将最小节点的value替换待删除节点的value
            searchDeleteNode.value = searchBinaryTreeMin.value;
        } else {
            //删除的节点有一个子节点
            //如果待删除节点是根节点
            if (searchDeleteParentNode == null) {
                //判断子节点
                if (searchDeleteNode.left != null) {
                    //子节点为左节点
                    root = searchDeleteNode.left;
                } else {
                    //子节点为右节点
                    root = searchDeleteNode.right;
                }
            } else {//待删除节点为非根节点
                //判断待删除节点是其父节点的左右子节点
                if (searchDeleteParentNode.left == searchDeleteNode) {
                    //左子节点
                    //判断待删除节点拥有的子节点
                    if (searchDeleteNode.left != null) {
                        //左子节点
                        searchDeleteParentNode.left = searchDeleteNode.left;
                    } else {
                        //右子节点
                        searchDeleteParentNode.left = searchDeleteNode.right;
                    }
                } else {
                    //右子节点
                    //判断待删除节点拥有的子节点
                    if (searchDeleteNode.left != null) {
                        //左子节点
                        searchDeleteParentNode.right = searchDeleteNode.left;
                    } else {
                        //右子节点
                        searchDeleteParentNode.right = searchDeleteNode.right;
                    }
                }
            }
        } 
    }

    //查找当前二叉排序树最小的value
    public Node searchBinaryTreeMin(Node node) {

        if (node == null) {
            System.out.println("二叉排序树为null");
            return null;
        }
        //最小值在左子节点
        while (node.left != null) {
            //node向左子树移动
            node = node.left;
        }
        return node;
    }

    //查找要删除的节点
    public Node searchDeleteNode(int value, Node node) {

        //判断根节点是否为null
        if (root == null) {
            System.out.println("二叉树为null");
            return null;
        }
        //判断当前节点是否是否为null
        if (node == null) {
            return null;
        }
        //判断当前节点是否是删除的节点
        if (node.value == value) {
            return node;
        } else if (node.value > value) {
            //向左递归查找
            return searchDeleteNode(value, node.left);
        } else {
            //向右递归查找
            return searchDeleteNode(value, node.right);
        }
    }

    //查找要删除节点的父节点
    public Node searchDeleteParentNode(int value, Node node) {

        //判断根节点是否为null
        if (root == null) {
            System.out.println("二叉树为null");
            return null;
        }
        //判断当前节点是否为null
        if (node == null) {
            return null;
        }
        //判断当前节点是否是待删除节点的父节点
        if ((node.left != null && node.left.value == value) || (node.right != null && node.right.value == value)) {
            return node;
        }
        if (node.value > value) {
            //向左递归
            return searchDeleteParentNode(value, node.left);
        } else if (node.value < value) {
            //向右递归
            return searchDeleteParentNode(value, node.right);
        } else {
            //没有父节点
            return null;
        }
    }

    //查找要删除节点的父节点(同时也可以找到删除节点)
    /* public Node searchDeleteParentNode(int value, Node node) {

        //判断根节点是否为null
        if (root == null) {
            System.out.println("二叉树为null");
            return null;
        }
        //判断当前节点是否为null
        if (node == null) {
            return null;
        }
        //判断当前节点是否有左子节点
        if (node.left != null) {
            //比较左子节点的value
            if (node.left.value == value) {
                return node;
            } else if (node.left.value > value) {
                //向左递归查找
                return searchDeleteParentNode(value, node.left);
            } else {
                //向右递归查找
                return searchDeleteParentNode(value, node.right)
            }
        }
        //判断当前节点是否有右子节点
        if (node.right != null) {
            //比较右子节点的value
            if (node.right.value == value) {
                return node;
            } else if (node.right.value > value) {
                //向左递归查找
                return searchDeleteParentNode(value, node.left);
            } else {
                //向右递归查找
                return searchDeleteParentNode(value, node.right);
            }
        } 
    } */

    //中序遍历
    public void infixOrder(Node node) {

        //判断根节点是否为null
        if (root == null) {
            System.out.println("二叉树为null");
            return;
        }
        
        if (node != null) {
            //向左递归
            infixOrder(node.left);
            //输出当前节点
            System.out.println(node);
            //向右递归
            infixOrder(node.right);
        }
    }

    //添加节点
    public void addNode(Node node) {

        //判断根节点是否为null
        if (root == null) {
            //当前加入节点为根节点
            root = node;
        } else {
            //正常添加
            root.addNode(node);
        }
    }

    public Node getRoot() {
        return root;
    }
}

//节点
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

    //添加节点
    public void addNode(Node node) {

        //判断node是否为null
        if (node == null) {
            return;
        }

        //比较加入节点与当前节点
        if (this.value > node.value) {
            //判断当前节点左子节点是否为null
            if (this.left == null) {
                this.left = node;
            } else {
                //向左递归添加
                this.left.addNode(node);
            }
        } else {
            //判断当前节点右子节点是否为null
            if (this.right == null) {
                this.right = node;
            } else {
                //向右递归添加
                this.right.addNode(node);
            }
        }
    }
}
