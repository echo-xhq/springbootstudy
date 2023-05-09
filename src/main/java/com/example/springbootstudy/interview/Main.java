package com.example.springbootstudy.interview;

import com.xhq.service.impl.MyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xhq
 * @version 1.0
 * @description Create by 2023/3/1 15:40
 */
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Autowired
    private static  MyServiceImpl myService;

    public  static void main(String[] args){
        int[] array = {3,2,4,1,5,6,7,9,8,0};
        myService.quickSort(array);
        log.info("排序后的数组"+array);
    }


}
