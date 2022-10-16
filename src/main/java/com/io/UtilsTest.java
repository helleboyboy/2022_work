package com.io;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-16:2:02
 * @Describe:
 */
public class UtilsTest {
    /**
     *  Files类： File类的工具类
     *      更多的功能可以看源码!
     */
    @Test
    public void testFiles() throws IOException {
        // 统计文件大小！
        long size = Files.size(Paths.get("D:\\test\\io\\utils\\a.txt"));
        System.out.println(size);
//        删除已经存在的文件
        boolean b = Files.deleteIfExists(Paths.get("D:\\test\\io\\utils\\b1.txt"));
        System.out.println("b1.txt has been deleted !");
//        新建空文件
        Path path = Files.createFile(Paths.get("D:\\test\\io\\utils\\b1.txt"));
//        删除目录
        Files.deleteIfExists(Paths.get("D:\\test\\io\\utils\\newDir"));
//        新建目录
        Path path2 = Files.createDirectory(Paths.get("D:\\test\\io\\utils\\newDir"));
        System.out.println(path2.getFileName());
        System.out.println(Files.isDirectory(path2));
        System.out.println(path.toAbsolutePath());
//        复制文件 文件 -> 文件
        Path path1 = Files.copy(
                Paths.get("D:\\test\\io\\utils\\a.txt"),
                Paths.get("D:\\test\\io\\utils\\c.txt"),
                StandardCopyOption.REPLACE_EXISTING);
        System.out.println(path1.getFileName());
        byte[] bytes = "hello,world!".getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//        输入流到文件的拷贝,返回的是流的字节大小！
        long len = Files.copy(
                byteArrayInputStream,
                Paths.get("D:\\test\\io\\utils\\c1.txt"),
                StandardCopyOption.REPLACE_EXISTING);
        System.out.println(len);

    }


    /**
     *  Paths类： Path类的工具类
     */
    @Test
    public void testPaths(){
//        paths是path的工具类
        Path path = Paths.get("D:\\test\\io\\utils\\c1.txt");
//        Path 与 File 之间的转换!
        File file = path.toFile();
        System.out.println(file.getAbsolutePath());
        System.out.println(path.toString());
    }

    /**
     *  引入common-io
     *   如果不了解,可以去maven repository页面找到对应的maven 依赖,查看主页面的 HomePage 链接;里面有文档!!!
     */
    @Test
    public void testThird() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("D:\\test\\io\\utils", "third.txt"));
            fileOutputStream = new FileOutputStream(new File("D:\\test\\io\\utils", "thirdRes.txt"));
            int len = IOUtils.copy(fileInputStream, fileOutputStream);
            System.out.println(len);
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
}
