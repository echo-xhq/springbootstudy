package com.example.springbootstudy.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadAndWriteFile {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("read.txt");
        FileChannel readChanel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("write2.txt");
        FileChannel writeChanel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true){
            byteBuffer.clear();

            int read = readChanel.read(byteBuffer);
            if (read == -1){
                break;
            }
            byteBuffer.flip();
            writeChanel.write(byteBuffer);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
