package com.inet;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-16:19:55
 * @Describe:
 */
public class InetTest {

    /**
     *  获取ip及域名,引入 InetAddress, 私有构造器!
     *      两个方法:
     *          1. getHostName
     *          2. getHostAddress
     * @throws UnknownHostException
     */
    @Test
    public void testInetAddress()  {
        InetAddress localHost = null;
        try {
//        获取本机InetAddress对象
            localHost = InetAddress.getLocalHost();
//        获取本机的域名
            System.out.println(localHost.getHostName());
//        获取本机的ip地址
            System.out.println(localHost.getHostAddress());
            System.out.println(localHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        InetAddress inetAddress = null;
        try {
            String host = "leader";
            inetAddress = InetAddress.getByName(host);
            System.out.println(inetAddress.getHostName());
            System.out.println(inetAddress.getHostAddress());
            System.out.println(inetAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        InetAddress inetAddress1 = null;
        try {
            String sggHost = "www.atguigu.com";
            inetAddress1 = InetAddress.getByName(sggHost);
            System.out.println(inetAddress1.getHostName());
            System.out.println(inetAddress1.getHostAddress());
            System.out.println(inetAddress1);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        InetAddress inetAddress3 = null;
        try {
            String noExistHost = "192.168.52.20";
            inetAddress3 = InetAddress.getByName(noExistHost);
            System.out.println(inetAddress3);
            System.out.println(inetAddress3.getHostAddress());
            System.out.println(inetAddress3.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }



}
