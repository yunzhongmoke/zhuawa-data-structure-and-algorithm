package com.w.sparseArray;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SparseArray {
    public static void main(String[] args) {
        
        //创建一个原始数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2; 
        chessArr1[4][5] = 2;

        //输出原始二维数组
        System.out.println("原始二维数组~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }

            System.out.println();
        }

        //将二维数组转化为稀疏数组
        //1.先遍历二维数组，得到非零的数据
        int sum = 0;//记录非零的数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;
        //遍历原始二维数组，将非零数据存入稀疏数组中
        int count = 0;//用于记录非零数的个数
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println("得到的稀疏数组为~~~");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        String filePath = "f:\\array.txt";
        //将稀疏数组保存至磁盘中
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            for (int[] row : sparseArr) {
                String dataString = "";
                for (int data : row) {
                    dataString += data + " ";
                }
                dataString += "\n";
                outputStream.write(dataString.getBytes(), 0, dataString.getBytes().length);
                // outputStream.flush();
            }

            //关闭流
            outputStream.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        

        //从磁盘中读取稀疏数组
        //创建对应的稀疏数组
        int[][] sparseArr2 = null;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            byte[] bytes = new byte[1024];
            int readLen = 0;
            while ((readLen = inputStream.read(bytes)) != -1){
                //读取到所有的数据
                String dataString = new String(bytes, 0, readLen);
                // System.out.println(dataString);
                //将所有的数据按每行分割
                String[] lineDatas = dataString.split("\n");
                

                // for (String lineData : lineDatas) {
                //     String[] datas = lineData.split(" ");
                //     for (String data : datas) {
                //         System.out.println(data);
                //     }
                //     System.out.println();
                // }

                //初始化sparseArr2数组
                //获取每行的数据数量(即数组的列数)
                int lineNumber = lineDatas[0].split(" ").length;
                sparseArr2 = new int[lineDatas.length][lineNumber];
                //赋值
                for (int i = 0; i < lineDatas.length; i++) {
                    //将每行的数据按空格分割
                    String[] lineData = lineDatas[i].split(" ");
                    for (int j = 0; j < lineData.length; j++) {
                        sparseArr2[i][j] = Integer.parseInt(lineData[j]);
                        // System.out.println(sparseArr2[i][j]);
                    }
                }
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //遍历读取后的稀疏数组
        System.out.println("读取文件后生成的稀疏数组");
        for (int[] row : sparseArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        
        /**
         * 将稀疏数组转化为二维数组
         * 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
         * 2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组
         */
        //创建数组
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        //赋值，从第二行开始
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }
        //打印转化后的原始数组
        System.out.println("转化后的原始数组~~~");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }




    }
}
