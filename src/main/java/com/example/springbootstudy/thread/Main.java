package com.example.springbootstudy.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author xhq
 * @version 1.0
 * @description Create by 2023/2/28 21:35
 */
public class Main {

    private final static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        ReadFileThread readFileThread = new ReadFileThread("read.txt",countDownLatch);
        Thread thread = new Thread(readFileThread);
        Thread thread1 = new Thread(readFileThread);


        thread.start();
        thread1.start();

        countDownLatch.await();

//        new ThreadPoolExecutor();
    }
}
