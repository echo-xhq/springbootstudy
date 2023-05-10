package com.example.springbootstudy.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyFile {

    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream("D:\\Echo\\Pictures\\卡通.jpg");
        FileOutputStream outputStream = new FileOutputStream("D:\\Echo\\Pictures\\拷贝卡通.jpg");
        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());

        inputStreamChannel.close();
        outputStreamChannel.close();
        inputStream.close();
        outputStream.close();

    }
}
