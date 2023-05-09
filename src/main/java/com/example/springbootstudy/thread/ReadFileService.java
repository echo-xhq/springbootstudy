package com.example.springbootstudy.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author xhq
 * @version 1.0
 * @description Create by 2023/2/27 21:55
 */
@Component
public class ReadFileService {

    private final static Logger log = LoggerFactory.getLogger(ReadFileService.class);


    public void  read(String pathName) throws IOException {
        long start = System.currentTimeMillis();
        FileReader fileReader = new FileReader(pathName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            log.info(line);
        }
        bufferedReader.close();
        long end = System.currentTimeMillis();
        log.info("单线程读取文件消耗的时间：{}",end-start);
    }
}
