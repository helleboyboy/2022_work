package com.io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-11:22:07
 * @Describe:
 *  总结：
 *      文本文件用字符流来读写，反例：字符流读写jpg文件
 *      非文本文件用字节流来读写；反例：字节流读写含中文文件乱码(输出打印会乱码，复制是不会乱码的)
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


    @Test
    public void testFileWriter() {
        String filePath = "D:\\test\\io";
        File file = new File(filePath, "testFileWriter.txt");
        FileWriter fileWriter = null;
        try {
//            是否为追加！覆盖
//            fileWriter = new FileWriter(file, false);
            fileWriter = new FileWriter(file, true);
            fileWriter.write("hello, world");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 利用FileReader和FileWriter来进行读写文件内容
     * 记得关闭文件流，否则会出现目标为空白文件
     */
    @Test
    public void testFileReaderAndFileWriter() {
        String filePath = "D:\\test\\io";
        File file = new File(filePath, "testFileReader.txt");
        File tarFile = new File(filePath, "testFileWriterTar.txt");
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(file);
            fileWriter = new FileWriter(tarFile, true);
            char[] charBuffer = new char[10];
            int len = 0;
            while ((len = fileReader.read(charBuffer)) != -1){
                String tempRes = new String(charBuffer, 0, len);
                fileWriter.write(tempRes);
            }
            System.out.println("finished");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
     * 测试FileInputStrea
     *  读入缓冲区的总字节数，如果由于已到达文件结尾而没有更多数据，则为-1。
     *  中文会乱码
     */
    @Test
    public void testFileInputStream() {
        String filePath = "D:\\test\\io";
        File file = new File(filePath, "testFileReader.txt");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] byteBuffer = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(byteBuffer)) != -1){
                String s = new String(byteBuffer, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 测试FileInputStrea
     *  读入缓冲区的总字节数，如果由于已到达文件结尾而没有更多数据，则为-1。
     *  需要转为char类型
     *  中文会乱码
     */
    @Test
    public void testFileInputStream1() {
        String filePath = "D:\\test\\io";
        File file = new File(filePath, "testFileReader.txt");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            int len = 0;
            StringBuffer sb = new StringBuffer();
            while ((len = fileInputStream.read()) != -1){
                sb.append((char) len);
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试FileInputStream与FileOutputStream
     *  复制非文本文件
     */
    @Test
    public void testFileInputStreamAndFileOutputStream() {
        String filePath = "D:\\test\\io";
        File file = new File(filePath, "a.png");
        File dstFile = new File(filePath, "b.png");
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream(dstFile, false);
            byte[] byteBuffer = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(byteBuffer)) != -1){
                fileOutputStream.write(byteBuffer, 0, len);
            }
            System.out.println("finished");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void copyFile(String parentDir, String srcFileName, String dstFileName){
        String filePath = parentDir;
        File file = new File(filePath, srcFileName);
        File dstFile = new File(filePath, dstFileName);
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream(dstFile, false);
            byte[] byteBuffer = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(byteBuffer)) != -1){
                fileOutputStream.write(byteBuffer, 0, len);
            }
            System.out.println("finished");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCopyFileMethod(){
        String parentDir = "D:\\test\\io";
        String srcFileName = "a.png";
        String dstFileName = "b.png";
        long start = System.currentTimeMillis();
        copyFile(parentDir, srcFileName, dstFileName);
        long end = System.currentTimeMillis();
        System.out.println((end - start));
    }
}