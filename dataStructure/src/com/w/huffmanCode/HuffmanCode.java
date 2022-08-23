package com.w.huffmanCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {

    //压缩中最后一个byte数组对应的二进制字符串的长度
    static int stringLength = 0;
    
    public static void main(String[] args) {
        
        // String content = "i like like like java do you like a java";
        // byte[] contentBytes = content.getBytes();

        // System.out.println(Arrays.toString(contentBytes));
        // List<Node> nodes = getNodes(contentBytes);
        // System.out.println(nodes);
        // Node root = createHuffmanTree(nodes);
        // preOrder(root);
        // // getCodes(root, "", new StringBuilder());
        // getCodes(root);
        // System.out.println(huffmanCodes);
        // byte[] byteZip = zip(contentBytes, huffmanCodes);
        // System.out.println(Arrays.toString(byteZip));

        // byte[] huffmanZip = huffmanZip(contentBytes);

        // System.out.println(Arrays.toString(huffmanZip));
        // System.out.println(Integer.toBinaryString(-88));/* 10101000 */
        // System.out.println((byte) Integer.parseInt("10101000", 2));
        // byte[] bytes = decode(huffmanCodes, huffmanZip);
        // System.out.println(new String(bytes));

        //测试文件压缩和解压
        // String srcFile = "F:\\洛克王国.png";
        // String destFile = "F:\\luoke.zip";
        // zipFile(srcFile, destFile);
        // System.out.println("压缩成功~");

        String srcFile = "F:\\luoke.zip";
        String destFile = "F:\\luoke.png";
        unZipFile(srcFile, destFile);
        System.out.println("解压成功~");
    }

    //解压文件
    private static void unZipFile(String srcFile, String destFile) {

        //定义输入流
        InputStream is = null;
        ObjectInputStream ois = null;
        //定义输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(srcFile);
            //创建对象输入流
            ois = new ObjectInputStream(is);
            //读取压缩前二进制字符串长度
            stringLength = (int) ois.readObject();
            System.out.println("stringLength = " + stringLength);
            //读取huffman编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            // System.out.println("huffmanCodes = " + huffmanCodes);
            //读取byte数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // System.out.println("huffmanBytes = " + Arrays.toString(huffmanBytes));
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //创建文件输出流
            os = new FileOutputStream(destFile);
            //写数据到destFile文件中
            os.write(bytes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (os != null) {
                    os.close();
                }
                if (ois != null) {
                    ois.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    //压缩文件
    private static void zipFile(String srcFile, String destFile) {

        //定义输入流
        InputStream is = null;
        //定义输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //读取文件
        try {
            //创建文件输入流
            is = new FileInputStream(srcFile);
            //创建数组用于存放数组
            byte[] bytes = new byte[is.available()];
            //将读取的数据存入数组
            is.read(bytes);
            //对源文件进行压缩
            byte[] huffmanBytes = huffmanZip(bytes);
            //创建文件输出流，存放压缩后的文件
            os = new FileOutputStream(destFile);
            //创建对象输出流
            oos = new ObjectOutputStream(os);
            //存入压缩前二进制字符串长度
            oos.writeObject(stringLength);
            //存入huffman编码表
            oos.writeObject(huffmanCodes);
            //存入压缩后的字节数组
            oos.writeObject(huffmanBytes);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (oos != null) {
                    oos.close();
                }
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

    //压缩数据的解码
    /**
     * @param huffmanCodes huffman编码表
     * @param huffmanBytes huffman编码
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

        //得到huffmanBytes对应的二进制字符串
        //创建StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        //遍历huffmanBytes
        // for (byte b : huffmanBytes) {
        //     //定义一个字符串，用于接收转化后的字符
        //     String str = "";
        //     //判断其是否为最后一个元素
        //     if (b == huffmanBytes[huffmanBytes.length - 1]) {
        //         str = byteToBitString(b, b > 0, true, stringLength);
        //     } else {
        //         str = byteToBitString(b, b > 0, false, stringLength);
        //     }
        //     //拼接字符串
        //     stringBuilder.append(str);
        // }
        for (int i = 0; i < huffmanBytes.length; i++) {
            //定义一个字符串，用于接收转化后的字符
            String str = "";
            //判断其是否为最后一个元素
            if (i == huffmanBytes.length - 1) {
                str = byteToBitString(huffmanBytes[i], huffmanBytes[i] >= 0, true, stringLength);
            } else {
                str = byteToBitString(huffmanBytes[i], huffmanBytes[i] >= 0, false, stringLength);
            }
            //拼接字符串
            stringBuilder.append(str);
        }
        // System.out.println(stringBuilder);
        //将huffman编码表key - value调换，反向查询
        Map<String, Byte> map = new HashMap<>();
        //匹配huffman编码表
        huffmanCodes.entrySet().forEach(item -> {
            map.put(item.getValue(), item.getKey());
        });
        //将编码匹配反转后的编码表
        //创建一个集合，用于存放数据
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            //计数
            int count = 0;
            //用于接收字符串
            String key = "";
            while (map.get(key) == null) {
                count++;
                key = stringBuilder.substring(i, i + count);
            }
            //将key加入数组中
            list.add(map.get(key));
            i += count;
        }
        //创建一个数组
        byte[] bytes = new byte[list.size()];
        //遍历lis集合，将数据存放在byte数组中
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        //返回数组
        return bytes;
    }

    //将一个byte转为二进制字符串
    /**
     * @param b
     * @param flag1 判断为正数还是复数
     * @param flag2 是否为数组中的最后一个数据
     * @param stringLength 转化前字符串长度
     * @return
     */
    private static String byteToBitString(byte b, boolean flag1, boolean flag2, int stringLength) {

        //将b转为int类型
        int temp = b;
        //如果temp为正数，高位需要补零，如果既是正数，又是byte数组的最后一位，则不需要补零
        if (flag1 && !flag2) {
            temp |= 256;
        }
        //将temp转为二进制字符串
        String str = Integer.toBinaryString(temp);
        //判断其是否是byte数组的最后一个
        if (!flag2) {
            return str.substring(str.length() - Byte.SIZE);
        } else {
            return str.substring(str.length() - stringLength);
        }
    }

    //封装生成huffman编码的方法
    public static byte[] huffmanZip(byte[] bytes) {

        //将byte数组转为List集合
        List<Node> nodes = getNodes(bytes);
        //生成huffman tree
        Node root = createHuffmanTree(nodes);
        //得到Huffman编码表
        Map<Byte, String> huffmanCodes = getCodes(root);
        //得到对应的Huffman编码
        byte[] huffmanBytes = zip(bytes, huffmanCodes);

        return huffmanBytes;
    }
    
    //将字符串对应的byte[]数组，通过Huffman编码表，返回huffman编码
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        //创建StringBuilder用于字符串拼接
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes，通过huffman编码表，得到对应的huffman编码
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //数组的大小
        int length = (stringBuilder.length() + 7) / 8;
        //创建数组，存放huffman编码
        byte[] huffmanCodeBytes = new byte[length];
        //存放Huffman编码
        //遍历数组的索引
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            //stringBuilder末尾不足八位，防止数组越界
            if (i + 8 > stringBuilder.length()) {
                //压缩中最后一个byte数组对应的二进制字符串的长度
                stringLength = stringBuilder.length() - i;
                huffmanCodeBytes[index] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
            } else {
                huffmanCodeBytes[index] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
            }
            //数组索引后移
            index++;
        }
        return huffmanCodeBytes;
    }

    //重载getCodes方法
    /**
     * @param root 二叉树根节点
     * @return huffman编码表
     */
    private static Map<Byte, String> getCodes(Node root) {
        if (root != null) {
            getCodes(root, "", new StringBuilder());
        } else {
            System.out.println("二叉树为null");
        }
        return huffmanCodes;
    }

    //将huffman 编码表存放在Map<Byte, String>形式集合中
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    /**
     * 将传入node节点的所有叶子节点的huffman编码得到，并存入huffmanCodes集合中
     * @param node 传入节点
     * @param code 路径，左 0， 右 1
     * @param stringBuffer 拼接code编码
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {

        //创建StringBuilder
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //拼接
        stringBuilder2.append(code);
        //判断传入节点是否为null
        if (node != null) {
            //非空
            //是否是二叉树叶子节点
            if (node.data == null) {
                //非叶子节点
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                //叶子节点
                //将huffman编码添加至map集合中
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //前序遍历
    private static void preOrder(Node node) {

        if (node != null) {
            System.out.println(node);
            //向左递归
            preOrder(node.left);
            //向右递归
            preOrder(node.right);
        }
    }

    //创建huffman tree
    private static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            //对集合按权值进行排序
            Collections.sort(nodes);
            //取出前两个权值最小值
            Node left = nodes.get(0);
            Node right = nodes.get(1);

            //构建一颗新的二叉树,根节点没有data
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;

            //删除已经处理过的两个节点
            nodes.remove(0);
            nodes.remove(0);
            
            //将新的节点加入到nodes集合中
            nodes.add(parent);
        }
        //返回nodes集合中的最后一个数据
        return nodes.get(0);
    }

    // 将byte数组转为List集合
    private static List<Node> getNodes(byte[] bytes) {

        //创建一个Map
        Map<Byte, Integer> counts = new HashMap<>();
        //遍历bytes
        for(byte b : bytes) {
            counts.merge(b, 1, Integer::sum);
        }
        //创建一个集合
        List<Node> nodes = new ArrayList<>();
        //遍历Map，将其加入nodes集合中
        counts.entrySet().forEach(item -> {
            nodes.add(new Node(item.getKey(), item.getValue()));
        });

        return nodes;
    }
}

class Node implements Comparable<Node> {
    Byte data;//存放数据
    int weight;//权值，字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + ", weight=" + weight + "]";
    }

    //按权值排序
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

}
// 1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100