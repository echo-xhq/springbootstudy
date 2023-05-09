package com.example.springbootstudy.token;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Echo
 * @version 1.0
 * @description Create by 2022/7/19 10:41
 */
public class Main {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        System.out.println(set.add("abc")); // true
        System.out.println(set.add("xyz")); // true
        System.out.println(set.add("xyz")); // false，添加失败，因为元素已存在
        System.out.println(set.contains("xyz")); // true，元素存在
        System.out.println(set.contains("XYZ")); // false，元素不存在
        System.out.println(set.remove("hello")); // false，删除失败，因为元素不存在
        System.out.println(set.size()); // 2，一共两个元素
//        HashMap<String,String> map = new HashMap<>(10);
//        System.out.println(map.get("apple"));
        //String token = ssoUtils.getToken("18070401585");
        //System.out.println(token);
        /*int temp = 2400;
        double rate = temp / 3000.00;
        boolean
        if (rate >= 0.8){
            System.out.println("满足条件");
        }*/
    }
}
