package com.w.hashTable;

import java.util.Scanner;

public class HashTableDemo {
    
    public static void main(String[] args) {
        
        //创建hashTable
        HashTable hashTable = new HashTable(7);

        //编写一个菜单
        String key = "";//用于接收输入的值
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员"); 
            System.out.println("list: 显示雇员"); 
            System.out.println("find: 查找雇员"); 
            System.out.println("delete: 删除雇员"); 
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.print("输入id: ");
                    int id = scanner.nextInt();
                    System.out.print("输入名字: ");
                    String name = scanner.next();
                    //创建Employee对象
                    Employee employee = new Employee(id, name);
                    //添加
                    hashTable.addOrder(employee);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.print("输入查询的id: ");
                    id = scanner.nextInt();
                    hashTable.findEmployeeById(id);
                    break;
                case "delete":
                    System.out.print("输入删除的id: ");
                    id = scanner.nextInt();
                    hashTable.deleteEmployeeById(id);
                    break;
                case "exit":
                    //关闭Scanner
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("输入有误，请重新输入");
            }
        }
    }
}

//管理多条链表
class HashTable {

    //创建EmployeeLinkedList数组
    private EmployeeLinkedList[] employeeLinkedLists;
    private int size;//数组的大小

    //构造器
    public HashTable(int size) {
        this.size = size;
        employeeLinkedLists = new EmployeeLinkedList[size];
        //初始化数组
        for (int i = 0; i < size; i++) {
            employeeLinkedLists[i] = new EmployeeLinkedList();
        }
    }

    //编写散列函数，取模法
    public int HashFunction(int id) {
        return id % size;
    }

    //添加雇员
    public void add(Employee employee) {

        //根据员工id，得到员工应该添加在哪条链表
        int employeeLinkedListNo = HashFunction(employee.id);
        //将employee添加到指定的链表
        employeeLinkedLists[employeeLinkedListNo].add(employee); 
    }

    //按顺序添加雇员
    public void addOrder(Employee employee) {

        //根据员工id，得到员工应该添加在哪条链表
        int employeeLinkedListNo = HashFunction(employee.id);
        //将employee添加到指定的链表
        employeeLinkedLists[employeeLinkedListNo].addOrder(employee); 
    }

    //遍历所有链表
    public void list() {

        for (int i = 0; i < employeeLinkedLists.length; i++) {
            employeeLinkedLists[i].list(i);
            System.out.println();
        }
    }

    //根据id查找雇员
    public void findEmployeeById(int id) {

        //根据员工id，得到员工在哪条链表
        int employeeLinkedListNo = HashFunction(id);
        Employee employee = employeeLinkedLists[employeeLinkedListNo].findEmployeeById(id);
        if (employee == null) {
            System.out.println("没有该员工");
        } else {
            System.out.println("在第" + employeeLinkedListNo + "条链表中找到雇员" + employee);
        }
    }

    //根据id删除雇员
    public void deleteEmployeeById(int id) {
        //根据员工id，得到员工在哪条链表
        int employeeLinkedListNo = HashFunction(id);
        Employee employee = employeeLinkedLists[employeeLinkedListNo].deleteEmployeeById(id);
        if (employee == null) {
            System.out.println("没有该员工");
        } else {
            System.out.println("删除成功~");
            System.out.println("员工信息为：" + employee);
        }

    }
}

//雇员链表(单链表)
class EmployeeLinkedList {

    //创建一个头节点
    private Employee head;//默认为null

    //添加雇员到链表
    public void add(Employee employee) {

        //判断头节点是否为null
        if (head == null) {
            //为null，使其为头节点，退出函数
            head = employee;
            return;
        }

        //遍历链表，将其添加到至末尾
        //定义一个辅助节点
        Employee currentEmployee = head;
        while (currentEmployee.next != null) {
            //后移
            currentEmployee = currentEmployee.next;
        }
        //加入链表
        currentEmployee.next = employee;
    }

    //按顺序添加雇员到链表
    public void addOrder(Employee employee) {

        //判断头节点是否为null
        if (head == null) {
            //为null，使其为头节点，退出函数
            head = employee;
            return;
        }

        //跟头节点的id进行比较
        if (head.id > employee.id) {
            employee.next = head;
            head = employee;
            return;
        }

        //定义辅助节点
        Employee currentEmployee = head;
        //遍历链表
        while (currentEmployee.next != null) {
            if (currentEmployee.next.id > employee.id) {
                //找到添加的位置
                break;
            }
            //节点后移
            currentEmployee = currentEmployee.next;
        }
        //加入链表
        employee.next = currentEmployee.next;
        currentEmployee.next = employee;
    }

    //遍历链表
    public void list(int no) {

        if (head == null) {
            System.out.print("第" + no + "条链表为null");
            return;
        }

        System.out.print("第" + no + "条链表的信息为");

        //遍历链表
        //定义一个辅助节点
        Employee currentEmployee = head;
        while (currentEmployee != null) {
            System.out.printf("=> id = %d, name = %s\t", currentEmployee.id, currentEmployee.name);
            //辅助节点后移
            currentEmployee = currentEmployee.next;
        }
    }

    //根据id查找雇员
    public Employee findEmployeeById(int id) {

        // if (head == null) {
        //     //链表为null
        //     System.out.println("链表为null");
        //     return null;
        // }

        //定义辅助节点
        Employee currentEmployee = head;
        while (currentEmployee != null) {
            if (currentEmployee.id == id) {
                //找到了
                return currentEmployee;
            }
            //节点后移
            currentEmployee = currentEmployee.next;
        }

        //没有找到
        return null;

    }

    //根据id删除雇员
    public Employee deleteEmployeeById(int id) {

        //判断链表是否为null
        if (head == null) {
            System.out.println("链表为null");
            return null;
        }

        //定义删除节点
        Employee deleteEmployee = null;

        //如果删除的节点为头节点
        if (head.id == id) {
            deleteEmployee = head;
            head = head.next;
            return deleteEmployee;
        }

        //定义辅助节点
        Employee currentEmployee = head;
        //遍历链表
        while (currentEmployee.next != null) {
            if (currentEmployee.next.id == id) {
                //找到删除节点
                //保存删除节点
                deleteEmployee = currentEmployee.next;
                //删除节点
                currentEmployee.next = currentEmployee.next.next;
            }
            //节点后移
            currentEmployee = currentEmployee.next;
        }
        //返回删除节点
        return deleteEmployee;
    }
}

//雇员类
class Employee {

    public int id;
    public String name;
    public Employee next;//默认值为null

    //构造器
    public Employee (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {

        return "=> id = " + id + " name = " + name + "\t";
    }
}
