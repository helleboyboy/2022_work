package com.inet;

import org.junit.Test;

import java.io.*;
import java.net.*;
import java.util.Arrays;

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
        OutputStream outputStream = null;
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
            outputStream = socket.getOutputStream();
            outputStream.write("服务端收到数据了，感谢客户端的馈赠！".getBytes());
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
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        InetAddress serverInetAddress = null;
        try {
            serverInetAddress = InetAddress.getLocalHost();
            clientSocket = new Socket(serverInetAddress, serverPort);
            outputStream = clientSocket.getOutputStream();
            outputStream.write("大家好,我叫java页,别名为javaAndBigdata!".getBytes());
//            在传输玩后立即关闭输出流！！，否则会引起服务器端 的read方法 堵塞 在读输入的环节！！！
            clientSocket.shutdownOutput();
            System.out.println("客户端已经发送数据!请查看是否收到数据!");
            inputStream = clientSocket.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, len);
            }
            System.out.println("客户端已收到服务端的反馈：" + byteArrayOutputStream.toString());
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

    /**
     * udp方式的发送端
     * @throws IOException
     */
    @Test
    public void sender() {
        DatagramSocket datagramSocket = null;
        try {
//        新建套接字实例,不绑定主机或者端口
            datagramSocket = new DatagramSocket();
//        将数据装载在数据报实例中
            String info = "我是大数据页，别名为javaAndBigdata，我现在心情不好，只管发泄，不管别人感受！！！";
            byte[] buffer = info.getBytes();
            InetAddress targetAddress = InetAddress.getLocalHost();
            int port = 9999;
//        将数据发送到目的地,需要指明!
            DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length, targetAddress, port);
//        套接字实例发送数据报实例
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//        关闭资源
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    @Test
    public void receiver() {
        DatagramSocket datagramSocket = null;
        try {
            int port = 9999;
//        启动端口来接受数据报
            datagramSocket = new DatagramSocket(port);
            byte[] buffer = new byte[1024];
//        使用数组来接收发送端的数据报
            DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
//        套接字实例接收数据报实例
            datagramSocket.receive(datagramPacket);
//        获取数据报的数据内容
            byte[] data = datagramPacket.getData();
            System.out.println(new String(data, 0, data.length));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//        关闭资源
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

}
