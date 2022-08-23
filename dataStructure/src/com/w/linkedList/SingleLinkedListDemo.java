package com.w.linkedList;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByOrder(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList1.addByOrder(new HeroNode(3, "李逵", "黑旋风"));
        singleLinkedList1.addByOrder(new HeroNode(2, "林冲", "豹子头"));
        singleLinkedList1.addByOrder(new HeroNode(4, "卢俊义", "玉麒麟"));
        singleLinkedList1.list();

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(new HeroNode(5, "q", "q"));
        singleLinkedList2.addByOrder(new HeroNode(6, "w", "w"));
        singleLinkedList2.addByOrder(new HeroNode(8, "e", "e"));
        singleLinkedList2.addByOrder(new HeroNode(9, "r", "r"));
        singleLinkedList2.list();

        // System.out.println("修改后");
        // singleLinkedList.update(new HeroNode(1, "宋大哥", "及时雨"));
        // singleLinkedList.list();

        // System.out.println("删除节点");
        // singleLinkedList.delete(5);
        // // singleLinkedList.delete(1 );
        // singleLinkedList.list();

        // System.out.println("链表的长度：" + getLength(singleLinkedList.getHead()));

        // System.out.println("查找倒数第k个节点：" + findLastIndexNode(singleLinkedList.getHead(), 3));

        // System.out.println("反转后的：");
        // reverseList(singleLinkedList.getHead());
        // singleLinkedList.list();
        
        // System.out.println("逆序打印后：");
        // reversePrint(singleLinkedList.getHead());

        System.out.println("合并后：");
        SingleLinkedList mergeSingleLinkedList = merge(singleLinkedList2.getHead(), singleLinkedList1.getHead());
        mergeSingleLinkedList.list();

    }

    //获取单链表的节点的个数(不统计头节点)
    public static int getLength(HeroNode head) {
        //定义辅助变量
        HeroNode current = head;
        if (current.next == null) {
            return 0;
        }
        int length = 0;//用于记录个点个数
        while (current.next != null) {
            length++;
            //后移，遍历
            current = current.next;
        }
        return length;
    }

    //查找单链表中的倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //定义辅助变量
        HeroNode current = head;
        if (current.next == null) {
            //链表为空
            return null;
        }
        //定义一个节点与current相同，用于指向与current距离为index的位置
        HeroNode after = current;
        for (int j = 0; j < index; j++) {
            after = after.next;
        }
        while (true) {
            if (after == null) {
                //after指针指向null，current指向了倒数第index的位置
                break;
            }
            current = current.next;
            after = after.next;
        }

        return current;
    }

    //将单链表反转
    public static void reverseList(HeroNode head) {

        //定义辅助指针
        HeroNode current = head.next;
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (current == null || current.next.next == null) {
            return;
        }
        //指向当前节点的下一个节点
        HeroNode next = null;
        //定义一个新的头节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前端
        while (current != null) {
            //先暂时保存当前节点的下一个节点，因为后面需要使用
            next = current.next;
            //将current 的下一个节点指向新的链表的最前端
            current.next = reverseHead.next;
            //将current 连接到新的链表上
            reverseHead.next = current;
            //将current后移
            current = next;
        }
        //将head.next 指向 reverseHead.next, 实现单链表的反转
        head.next = reverseHead.next;
    }

    //单链表逆序打印
    //可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(HeroNode head) {

        //定义一个辅助指针
        HeroNode current = head.next;
        if (current == null) {
            //空链表
            return;
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        //将链表的所有节点压入栈
        while (current != null) {
            stack.push(current);
            //指针后移，遍历
            current = current.next;
        }
        //将栈中的节点进行打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //合并两个有序的单链表
    public static SingleLinkedList merge(HeroNode head1, HeroNode head2) {

        //创建一个SingleLinkedList对象
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //定义辅助指针
        HeroNode current1 = head1.next;
        HeroNode current2 = head2.next;
        //用于保存当前节点的下一个节点
        HeroNode next = null;
        //遍历单链表
        while (current1 != null) {
            next = current1.next;
            singleLinkedList.addByOrder(current1);
            current1 = next;
        }
        while (current2 != null) {
            next = current2.next;
            singleLinkedList.addByOrder(current2);
            current2 = next;
        }

        return singleLinkedList;
    }

    
}

class SingleLinkedList{

    //初始化一个头节点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     * 1. 找到当前链表的最后节点
     * 2. 将最后这个节点的next指向新的节点
     */
    public void add(HeroNode heroNode) {

        //head节点不能动，需要一个辅助节点遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null){
                break;
            }
            //没有找到，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    /**
     * 第二种方式，在添加英雄时，根据排名将英雄插入到指定位置
     * 如果有这个排名，则添加失败，并给出提示
     */
    public void addByOrder(HeroNode heroNode) {

        //借助辅助指针
        HeroNode temp = head;
        //标志添加的编号是否存在
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                //说明temp已经在链表的最后
                break;
            } 
            if (temp.next.no > heroNode.no) {
                //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                //编号已存在
                flag = true;
                break;
            }
            //后移，遍历当前链表
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {
            //编号已存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点信息，根据no编号来修改
     */
    public void update(HeroNode newHeroNode) {
        
        //定义一个辅助指针
        HeroNode temp = head;
        //判断是否为空
        if (temp.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp.next == null) {
                //全部遍历完
                break;
            }
            if (temp.next.no == newHeroNode.no) {
                //找到了
                flag = true;
                break;
            }
            //temp后移
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            //找到
            temp.next.name = newHeroNode.name;
            temp.next.nickname = newHeroNode.nickname;
        } else {
            //没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除节点
     */
    public void delete(int no) {
        //定义辅助指针
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除节点
        while (true) {
            if (temp.next == null) {
                //遍历完链表
                break;
            }
            if (temp.next.no == no) {
                //找到待删除的节点
                flag = true;
                break;
            }
            //temp后移，遍历链表
            temp = temp.next;
        }
        //判断flag
        if (flag) {
            //找到待删除节点，删除
            temp.next = temp.next.next;
        } else {
            //删除节点不存在
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    //显示链表(遍历)
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //借助辅助节点遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
         
    }
    
}

class HeroNode {

    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//next默认为null

    public HeroNode(int no, String name, String nickname) {

        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode : no = " + no + "，name = " + name + "，nickname = " + nickname;
    }
}
