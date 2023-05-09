package com.example.springbootstudy.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BIOServer {

    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 6, 3000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("服务器启动");

        while (true){
            System.out.println("线程ID："+Thread.currentThread().getId()+" 线程名字："+Thread.currentThread().getName());
            System.out.println("等待连接......");

            Socket accept = serverSocket.accept();
            System.out.println("连接客户端成功,客户端信息: "+accept.toString());
            if (accept.isConnected()){
                threadPoolExecutor.execute(() -> handle(accept));
            }
        }
    }

    public static void handle(Socket socket)  {
        System.out.println("正在处理数据.......");
        System.out.println("线程ID："+Thread.currentThread().getId()+" 线程名字："+Thread.currentThread().getName());
        byte[] bytes = new byte[1024 * 1024];
        try {
            InputStream inputStream = socket.getInputStream();

            while (true){
                System.out.println("线程ID："+Thread.currentThread().getId()+" 线程名字："+Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if (read != -1){
                    System.out.println("发送方:"+socket.toString()+"数据："+new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            System.out.println("关闭一个socket......");
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



    }
}
