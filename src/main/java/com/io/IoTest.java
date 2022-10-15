package com.io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

    public void copyFileByBufferedStream(String parentDir, String srcFileName, String dstFileName){
        File file = new File(parentDir, srcFileName);
        File dstFile = new File(parentDir, dstFileName);
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = bufferedInputStream.read(buffer)) != -1){
                bufferedOutputStream.write(buffer, 0, len);
            }
            bufferedOutputStream.flush();
            System.out.println("finished");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testBufferedInputStreamAndBufferedOutputStream() {
        String parentDir = "D:\\test\\io";
        String srcFileName = "a.png";
        String dstFileName = "d.png";
        long start = System.currentTimeMillis();
        copyFileByBufferedStream(parentDir, srcFileName, dstFileName);
        long end = System.currentTimeMillis();
        System.out.println((end - start));
    }


    public void copyFileByBufferedReaderAndWriter(String parentDir, String srcFileName, String dstFileName){
        File file = new File(parentDir, srcFileName);
        File dstFile = new File(parentDir, dstFileName);
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            FileReader fileReader = new FileReader(file);
            FileWriter fileWriter = new FileWriter(dstFile);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
            char[] charBuffer = new char[1024];
            int len = 0;
            while ((len = bufferedReader.read(charBuffer)) != -1){
                bufferedWriter.write(charBuffer, 0, len);
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testBufferedReaderAndBufferedWriter() {
        String parentDir = "D:\\test\\io";
        String srcFileName = "testFileReader.txt";
        String dstFileName = "testFileReader2.txt";
        long start = System.currentTimeMillis();
        copyFileByBufferedReaderAndWriter(parentDir, srcFileName, dstFileName);
        long end = System.currentTimeMillis();
        System.out.println((end - start));
    }


    @Test
    public void testWriterNewMethod() {
        String filePath = "D:\\test\\io";
        File file = new File(filePath, "testFileReader.txt");
        File dstFile = new File(filePath, "testFileReader3.txt");
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            FileReader fileReader = new FileReader(file);
            FileWriter fileWriter = new FileWriter(dstFile);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
//        String s = bufferedReader.readLine();
            String res = null;
            while ((res = bufferedReader.readLine()) != null){
//                默认没有换行符，\n或者newLine
                bufferedWriter.write(res);
//                增加换行符的功能
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字节流与字符流之间的转换
     *  字节流 -> 字符流 (输入流 inputStreamReader)
     *  字符流 -> 字节流 （输出流 outputStreamWriter）
     *  判断是字符流还是字节流？看后缀即可！！！
     */
    @Test
    public void testTransformStreamAndReader() {
        String filePath = "D:\\test\\io";
        File file = new File(filePath, "testFileReader.txt");
        File dstFile = new File(filePath, "testFileReader_.txt");
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
//            默认为utf-8bianma
            inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
//            修改编码 utf-8 编码转换为 gbk
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");
            char[] charBuffer = new char[1024];
            int len =0;
            while ((len = inputStreamReader.read(charBuffer)) != -1){
                String res = new String(charBuffer);
                System.out.print(res);
                outputStreamWriter.write(charBuffer);
                outputStreamWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * idea的测试junit无法输入字符，需要main方法才可以！
     * @param args
     */
    public static void main(String[] args) {
        BufferedReader bufferedReader = null;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            bufferedReader = new BufferedReader(inputStreamReader);
            boolean flag = true;
            while (flag){
                System.out.println("请输入你的字符串:");
                String s = bufferedReader.readLine();
                if ("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s)){
                    System.out.println("程序正常退出 !!! ");
                    flag = false;
    //                return;
                }else {
                    String res = s.toUpperCase();
                    System.out.println(res);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *  将输出到控制台的内容输出到文件！
     * @throws FileNotFoundException
     */
    @Test
    public void testPrintStream() throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(
                new File("D:\\test\\io", "print.txt"));
        PrintStream printStream = new PrintStream(fileOutputStream);
        // 重新分配标准输出流的输出地方,输出到文件
//        System.setOut(printStream);
        // 重新分配标准输出流的输出地方,输出到控制台
        System.setOut(System.out);
        for (int i = 0; i < 100; i++) {
            System.out.print((char)i);
        }
        printStream.close();
    }


    /**
     * 数据流 处理java的基本数据类型和字符串类型
     *  notice：
     *      读取的顺序要一致！
     * @throws IOException
     */
    @Test
    public void testDataStream() throws IOException {
        /**
         * 将内存的数据保存到文件，直接打开会乱码
         */
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\test\\io", "dataOut.txt"));
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        dataOutputStream.writeUTF("javaAndBigdata");
        dataOutputStream.flush();
        dataOutputStream.writeInt(25);
        dataOutputStream.flush();
        dataOutputStream.writeBoolean(true);
        dataOutputStream.flush();
        dataOutputStream.close();

        File srcFile = new File("D:\\test\\io", "dataOut.txt");
        FileInputStream fileInputStream = new FileInputStream(srcFile);
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readInt());
        System.out.println(dataInputStream.readBoolean());
        dataOutputStream.close();
    }


    /**
     * 利用对象流来说明Java的序列化机制
     *  序列化： 内存Java对象 保存到磁盘或者通过网络传输出去
     *  反序列化： 拿到已经序列化的文件，可以读取文件还原 为 内存中的java对象
     */
    @Test
    public void testObjectStream() {
        /**
         *  序列化
         */
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(
                            new File("D:\\test\\io", "objOut.dat")));
            String str = new String("你好呀，Java序列化");
            objectOutputStream.writeObject(str);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 反序列化
         */
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(
                            new File("D:\\test\\io", "objOut.dat")));
            String res = (String) objectInputStream.readObject();
            System.out.println(res);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testObjectOutputStreamByDiyClass(){
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(
                            new File("D:\\test\\io", "objByDiyOut.dat")));
//            Mother mother = new Mother("ycl", 50);
            Mother mother = new Mother("ycl", 50, false, new Son());
            objectOutputStream.writeObject(mother);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testObjectInputStreamByDiyClass(){
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(
                            new File("D:\\test\\io", "objByDiyOut.dat")));
            Mother res = (Mother) objectInputStream.readObject();
            System.out.println(res.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
