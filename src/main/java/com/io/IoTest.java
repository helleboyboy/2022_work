package com.io;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-11:22:07
 * @Describe:
 */
public class IoTest {
    /**
     * 一个个字符char来拿，效率很一般
     */
    @Test
    public void testFileReader() {
        String filePath = "D:\\test\\io";
        File file = new File(filePath, "testFileReader.txt");
        FileReader fileReader = null;
        try {
            boolean createFile = file.createNewFile();
            System.out.println(createFile);
            fileReader = new FileReader(file);
            int buffer = 0;
//        buffer = fileReader.read() //读取的一个字符！
            while ((buffer = fileReader.read()) != -1){
                System.out.print((char)buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 一次拿多点字符 ，效率有所提升
     * @throws IOException
     */
    @Test
    public void testFileReader2() {
        String filePath = "D:\\test\\io";
        File file = new File(filePath, "testFileReader.txt");
        FileReader fileReader = null;
        StringBuffer stringBuffer = null;
        try {
            fileReader = new FileReader(file);
            char[] charBuffer = new char[10];
            int len = 0;
            stringBuffer = new StringBuffer();
            while ((len = fileReader.read(charBuffer)) != -1){
    //            错误的做法，因为charBuffer数组后面的元素可能保存为之前的元素，造成错误
    //            System.out.print(new String(charBuffer));

    //            读取到的长度是多少就拿多少！！！多的与你无关！
                System.out.print(new String(charBuffer, 0, len));
                stringBuffer.append(charBuffer, 0, len);
            }
            System.out.println();
            System.out.println("===");
            System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
