package com.datastruction;

import com.csvreader.CsvWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Array2SparseArray {
    public static final Logger logger = Logger.getLogger("Array2SparseArray");


    /**
     * 初始化数组
     * @param row 行
     * @param column 列
     * @return 初始化数组，并返回数组
     */
    public static int[][] initChessArray(int row, int column){
        int whiteValue = 2;
        int blackValue = 5;
        int[][] chessArray = new int[row][column];
        chessArray[1][2] = whiteValue;
        chessArray[2][3] = blackValue;
        return chessArray;
    }


    /**
     * 罗列数组元素
     * @param chessArray 数组
     */
    public static void showChessArray(int[][] chessArray){
        for (int[] chessArrayOne : chessArray) {
            for (int i : chessArrayOne) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
    }


    /**
     * 获取数组中非零的元素个数
     * @param chessArray 数组
     * @return 数组中非零的元素个数
     */
    public static int getNonZeroNum(int[][] chessArray){
        int count = 0;
        for (int[] chessArrayOne : chessArray) {
            for (int i : chessArrayOne) {
                if (i != 0){
                    count ++;
                }
            }
        }
        return count;
    }


    /**
     *  普通数组转换为稀疏数组
     * @param chessArray 普通数组
     * @return 稀疏数组
     */
    public static int[][] array2SparseArray(int[][] chessArray){
        int sparseArrayLength = getNonZeroNum(chessArray) + 1;
        int[][] sparseArray = new int[sparseArrayLength][3];
        int rowValue = chessArray.length;
        int columnValue = chessArray[0].length;
        int count = 0;
        sparseArray[0][0] = rowValue;
        sparseArray[0][1] = columnValue;
        sparseArray[0][2] = getNonZeroNum(chessArray);
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0){
                    count ++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }
        return sparseArray;
    }


    public static void saveSparseArrayToCsv(int[][] sparseArray, String filePath){
        try (
                FileWriter fileWriter = new FileWriter(filePath);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            for (int[] sparseArrayOne : sparseArray) {
                StringBuilder stringBuilder = null;
                stringBuilder = new StringBuilder();
                for (int i : sparseArrayOne) {
                    stringBuilder.append(i);
                    stringBuilder.append(",");
                }
                String outputString = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
                stringBuilder.delete(0, stringBuilder.length());
                bufferedWriter.write(outputString);
                bufferedWriter.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int row = 10;
        int column = 11;
        int[][] chessArray = initChessArray(row, column);
        showChessArray(chessArray);
        String nonZeroNumInfo = String.format("数组非零个数:%d", getNonZeroNum(chessArray));
        logger.log(Level.INFO, nonZeroNumInfo);
        int[][] sparseArray = array2SparseArray(chessArray);
        showChessArray(sparseArray);
        String filePath = "D:\\test\\sparseArray.csv";
        saveSparseArrayToCsv(sparseArray, filePath);
    }
}
