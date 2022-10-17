package com.url;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-18:0:42
 * @Describe:
 */
public class UrlTest {
    /**
     * url基本方法及其构成!
     */
    @Test
    public void testUrl() {
        try {
            URL url = new URL("http://localhost:8080/examples/mybook.txt?a=a");
//        获取协议
            System.out.println(url.getProtocol());
//        获取ip
            System.out.println(url.getHost());
//        获取端口
            System.out.println(url.getPort());
//        获取文件路径
            System.out.println(url.getPath());
//        获取文件名
            System.out.println(url.getFile());
//        获取参数
            System.out.println(url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过url来下载文件
     */
    @Test
    public void testDownloadFileByUrl() {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            URL url = new URL("http://localhost:8080/examples/mybook.txt?a=a");
//        拿到HttpUrl连接对象
            urlConnection = (HttpURLConnection) url.openConnection();
//        开始连接
            urlConnection.connect();
//        程序获取输入流
            inputStream = urlConnection.getInputStream();
//        构建输出流实例
            fileOutputStream = new FileOutputStream(new File("D:\\test\\io", "book.txt"));
            byte[] buffer = new byte[1024];
            int len = 0;
            StringBuilder stringBuilder = new StringBuilder();
//        读写逻辑,此时len 为 -1
            while ((len = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, len);
                stringBuilder.append(new String(buffer, 0, len));
            }
            System.out.println(stringBuilder.toString());
            System.out.println("下载完成！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//        关闭资源
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}


