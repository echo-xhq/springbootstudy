package com.example.springbootstudy.thread;

import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xhq
 * @version 1.0
 * @description Create by 2023/3/2 21:06
 */
public class ThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                4,
                8,
                120,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        pool.submit(()->{
            try {
                RandomAccessFile rw = new RandomAccessFile(new File("read.txt"), "rw");
                byte[] bytes = new byte[20];
                rw.read(bytes);
                String result = new String(bytes);
                System.out.println(result);
                System.out.println(rw.getFilePointer());
                System.out.println(rw.length());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
