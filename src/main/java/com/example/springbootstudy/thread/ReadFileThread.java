package com.example.springbootstudy.thread;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author xhq
 * @version 1.0
 * @description Create by 2023/2/27 21:51
 */
public class ReadFileThread implements Runnable{

    private String pathName;
    private CountDownLatch countDownLatch;


    private ReadFileService readFileService = new ReadFileService();

    public ReadFileThread(String pathName,CountDownLatch countDownLatch) {
        this.pathName = pathName;
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void run() {
        synchronized (ReadFileService.class) {
            try {
                System.out.println("文件路径---->" + pathName);
                readFileService.read(pathName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        countDownLatch.countDown();
    }

}
