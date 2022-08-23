package com.w.linkedList;

public class DoubleLinkedListDemo {
    
    public static void main(String[] args) {
        
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        // doubleLinkedList.add(new HeroNode2(1, "宋江", "及时雨"));
        // doubleLinkedList.add(new HeroNode2(3, "李逵", "黑旋风"));
        // doubleLinkedList.add(new HeroNode2(2, "林冲", "豹子头"));
        // doubleLinkedList.add(new HeroNode2(4, "卢俊义", "玉麒麟"));
        // doubleLinkedList.list();

        System.out.println("有序添加：");
        doubleLinkedList.addByOrder(new HeroNode2(1, "宋江", "及时雨"));
        doubleLinkedList.addByOrder(new HeroNode2(3, "李逵", "黑旋风"));
        doubleLinkedList.addByOrder(new HeroNode2(2, "林冲", "豹子头"));
        doubleLinkedList.addByOrder(new HeroNode2(4, "卢俊义", "玉麒麟"));
        doubleLinkedList.list();

        // doubleLinkedList.update(new HeroNode2(1, "宋大哥", "及时雨"));
        // doubleLinkedList.list();

        System.out.println("删除节点：");
        doubleLinkedList.delete(4);
        // doubleLinkedList.delete(1);
        doubleLinkedList.list();
    }
}

class DoubleLinkedList{

    //初始化一个头节点
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //添加节点到双向链表(无序添加)
    public void add(HeroNode2 heroNode) {

        //head节点不能动，需要一个辅助节点遍历temp
        HeroNode2 temp = head;
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
        //将最后这个节点的next指向新的节点，新节点的pre指向temp
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //添加节点到双向链表(有序添加)
    public void addByOrder(HeroNode2 heroNode) {

        //借助辅助指针
        HeroNode2 temp = head;
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
            heroNode.pre = temp;
            //判断所添加的节点是否为最后一个节点
            if (heroNode.next != null) {
                heroNode.next.pre = heroNode;
            }
        }
    }

    //修改双向链表中的信息，根据no修改
    public void update(HeroNode2 newHeroNode) {
        
        //定义一个辅助指针
        HeroNode2 temp = head;
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

    //删除节点
    public void delete(int no) {
        //定义辅助指针
        HeroNode2 current = head.next;
        boolean flag = false;//标志是否找到待删除节点
        while (true) {
            if (current == null) {
                //遍历完链表
                break;
            }
            if (current.no == no) {
                //找到待删除的节点
                flag = true;
                break;
            }
            //temp后移，遍历链表
            current = current.next;
        }
        //判断flag
        if (flag) {
            //找到待删除节点，删除
            current.pre.next = current.next;
            //判断当前节点是否为最后一个节点
            if (current.next != null) {
                //不是，则执行
                current.next.pre = current.pre;
            }
            //彻底切断节点与双链表之间的联系
            current.next = null;
            current.pre = null;
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
        HeroNode2 temp = head.next;
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

class HeroNode2 {

    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//next默认为null
    public HeroNode2 pre;//pre默认为null

    public HeroNode2(int no, String name, String nickname) {

        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2 : no = " + no + "，name = " + name + "，nickname = " + nickname;
    }
}
