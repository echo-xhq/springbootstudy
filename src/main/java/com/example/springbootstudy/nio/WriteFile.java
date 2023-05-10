package com.example.springbootstudy.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class WriteFile {
    public static void main(String[] args) throws FileNotFoundException {

        String data = "在2023年，5月9日。学习使用同步非阻塞IO";

        try (FileOutputStream fileOutputStream = new FileOutputStream("D:\\Java练习项目xhq\\springbootstudy\\write1.txt")){
            FileChannel channel = fileOutputStream.getChannel();
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            allocate.put(data.getBytes());
            allocate.flip();
            channel.write(allocate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
