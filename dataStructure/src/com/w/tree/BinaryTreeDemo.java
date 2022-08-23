package com.w.tree;

public class BinaryTreeDemo {
    
    public static void main(String[] args) {
        
        //创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();

        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江"); 
        HeroNode node2 = new HeroNode(2, "吴用"); 
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        binaryTree.setRoot(root);

        //测试
        // System.out.println("前序遍历");
        // binaryTree.preOrder();

        // System.out.println("中序遍历");
        // binaryTree.infixOrder();

        // System.out.println("后序遍历");
        // binaryTree.postOrder();

        // System.out.println("前序遍历查找");
        // HeroNode result = binaryTree.preOrderSearch(4);
        // System.out.println(result);

        // System.out.println("中序遍历查找");
        // HeroNode result = binaryTree.infixOrderSearch(4);
        // System.out.println(result);

        // System.out.println("后序遍历查找");
        // HeroNode result = binaryTree.postOrderSearch(4);
        // System.out.println(result);

        System.out.println("删除节点");
        binaryTree.deleteNode(5);
        binaryTree.preOrder();
    }
}

//定义BinaryTree
class BinaryTree {

    private HeroNode root;//定义根节点

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        
        if (this.root == null) {
            System.out.println("二叉树为null，无法遍历");
        } else {
            this.root.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {

        if (this.root == null) {
            System.out.println("二叉树为null，无法遍历");
        } else {
            this.root.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        
        if (this.root == null) {
            System.out.println("二叉树为null，无法遍历");
        } else {
            this.root.postOrder();
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {

        if (this.root == null) {
            System.out.println("二叉树为null");
            return null;
        } else {
            return this.root.preOrderSearch(root, no);
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {

        if (this.root == null) {
            System.out.println("二叉树为null");
            return null;
        } else {
            return this.root.infixOrderSearch(root, no);
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {

        if (this.root == null) {
            System.out.println("二叉树为null");
            return null;
        } else {
            return this.root.postOrderSearch(root, no);
        }
    }

    //删除节点
    public void deleteNode(int no) {

        //判断根节点是否为删除节点
        if (root.getNo() == no) {
            //是,直接删除根节点
            root = null;
        } else {
            if (root.deleteNode(no)) {
                System.out.println("删除成功~");
            }
        }
    }

}

//定义HeroNode
class HeroNode {

    private int no;
    private String name;
    private HeroNode left;//左子节点
    private HeroNode right;//右子节点

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public HeroNode() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    

    @Override
    public String toString() {
        return "HeroNode [name=" + name + ", no=" + no + "]";
    }

    //前序遍历
    public void preOrder() {

        //先输出父节点
        System.out.println(this);

        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }

        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {

        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }

        //输出父节点
        System.out.println(this);

        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {

        //递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }

        //递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }

        //输出父节点
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(HeroNode root, int no) {

        //返回结果
        HeroNode result = null;
        if (root != null) {
            System.out.println("前序");
            //比较当前节点是不是
            if (root.no == no) {
                return root;
            }

            //递归向左子树查询
            result = preOrderSearch(root.left, no);
            //判断在左子树是否找到
            if (result != null) {
                return result;
            }

            //递归向右子树查询
            result = preOrderSearch(root.right, no);
        }
        return result;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(HeroNode root, int no) {

        //返回结果
        HeroNode result = null;
        if (root != null) {
            //递归向左子树查询
            result = infixOrderSearch(root.left, no);
            //判断在左子树是否找到
            if (result != null) {
                return result;
            }

            System.out.println("中序");
            //比较当前节点是不是
            if (root.no == no) {
                return root;
            }
            
            //递归向右子树查询
            result = infixOrderSearch(root.right, no);
        }
        return result;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(HeroNode root, int no) {

        //返回结果
        HeroNode result = null;
        if (root != null) {
            
            //递归向左子树查询
            result = postOrderSearch(root.left, no);
            //判断在左子树是否找到
            if (result != null) {
                return result;
            }

            //递归向右子树查询
            result = postOrderSearch(root.right, no);
            //判断在右子树是否找到
            if (result != null) {
                return result;
            }

            System.out.println("后序");
            //比较当前节点是不是
            if (root.no == no) {
                return root;
            }
        }
        return result;
    }

    //删除节点
    //判断是否找到删除节点
    private static boolean flag = false;
    public boolean deleteNode(int no) {

        if (this.left != null) {
            if (this.left.no == no) {
                //找到删除节点
                this.left = null;
                flag = true;
                return flag;
            }
            //向左子树递归删除
            this.left.deleteNode(no);
        }

        //判断是否找到
        if (flag) {
            return flag;
        }

        if (this.right != null) {
            if (this.right.no == no) {
                //找到删除节点
                this.right = null;
                flag = true;
                return flag;
            }
            //向右子树递归删除
            this.right.deleteNode(no);
        }
        return flag;
    }
}
