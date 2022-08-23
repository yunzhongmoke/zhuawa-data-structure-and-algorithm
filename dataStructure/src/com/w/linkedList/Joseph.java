package com.w.linkedList;

public class Joseph {
    
    public static void main(String[] args) {
        
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.currentBoy(1, 2, 5);
    }
}

//创建一个环形的单向列表
class CircleSingleLinkedList {

    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        //数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        //定义辅助指针，帮助构建环形链表
        Boy currentBoy = null;
        //使用for循环来创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                currentBoy = first;
            }
            //形成闭环了！(当只有一个节点的时候)
            currentBoy.setNext(boy);
            currentBoy = boy;
        }
        //形成闭环
        if (nums != 1) {
            currentBoy.setNext(first);
        }
    }

    //遍历当前的环形链表
    public void showBoy() {

        //判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        //定义辅助指针
        Boy currentBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d\n", currentBoy.getNo());
            if (currentBoy.getNext() == first) {
                //已经遍历完毕
                break;
            }
            //指针后移
            currentBoy = currentBoy.getNext();
        }
    }

    /**
     * 根据用户输入，计算出小孩出圈的顺序
     * @param startNo 表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void currentBoy(int startNo, int countNum, int nums) {

        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //辅助指针helper，事先应该指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) {
                //说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1(即startNo - 1)次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        while (true) {
            if (helper == first) {
                //说明圈中只有一个节点
                break;
            }
            //让first和helper同时移动countNum - 1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d 出圈\n", first.getNo());
            //将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n", first.getNo());

    }   
}

//创建一个Boy类，表示一个节点
class Boy {

    private int no;//编号
    private Boy next;//指向下一个节点，默认null

    public Boy (int no) {
        this.no = no;
    }

    public void setNo(int no) {
        this.no = no;
    }
    public int getNo() {
        return no;
    }
    public void setNext(Boy next) {
        this.next = next;
    }
    public Boy getNext() {
        return next;
    }
}