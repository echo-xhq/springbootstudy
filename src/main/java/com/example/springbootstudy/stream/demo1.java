package com.example.springbootstudy.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class demo1 {

    public static void main(String[] args) {

//        流存储的是有序的对象，跟list的类似，但是是惰性运算
        Stream<String> stringStream = Stream.of("a", "b", "c", "d");
        stringStream.forEach(System.out::println);

        Stream<String> stringStream1 = Arrays.stream(new String[]{"李白", "杜甫", "王安石"});
        ArrayList<String> strings = new ArrayList<>();
        strings.add("美国");
        strings.add("英国");
        strings.add("日本");
        Stream<String> stringStream2 = strings.stream();

        stringStream1.forEach(System.out::println);
        stringStream2.forEach(System.out::println);
    }
}
