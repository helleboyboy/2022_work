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

    /**
     * 引入网络编程的服务端!
     *  1. 新建ServerSocket实例
     *  2. 获取客户端Socket
     *  3. 获取输入流,获取客户端Socket对象的输出流及其内容
     *  4. 使用 ByteArrayOutputStream 来输出到控制台(ByteArrayOutputStream 可以避免乱码!!!)
     *  5. 关闭资源,从下而上!
     */
    @Test
    public void server() {
        int port = 9898;
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream socketInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            socketInputStream = socket.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4];
            int len = 0;
            while ((len = socketInputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, len);
            }
            String context = byteArrayOutputStream.toString();
            String host = socket.getInetAddress().getHostAddress();
            String res = String.format("客户端%s发来的数据为:%s", host, context);
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socketInputStream != null) {
                    socketInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试网络编程的客户端
     *  1. 新建Socket客户端实例
     *  2. 获取Socket客户端实例的输出流,并将内容输出到服务端
     *  3. 关闭资源
     */
    @Test
    public void client() {
        int serverPort = 9898;
        Socket clientSocket = null;
        OutputStream outputStream = null;
        InetAddress serverInetAddress = null;
        try {
            serverInetAddress = InetAddress.getLocalHost();
            clientSocket = new Socket(serverInetAddress, serverPort);
            outputStream = clientSocket.getOutputStream();
            outputStream.write("大家好,我叫java页,别名为javaAndBigdata!".getBytes());
            System.out.println("客户端已经发送数据!请查看是否收到数据!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
