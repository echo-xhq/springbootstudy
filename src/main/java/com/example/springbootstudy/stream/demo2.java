package com.example.springbootstudy.stream;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class demo2 {

    public static void main(String[] args) {

        Stream<Integer> generate = Stream.generate(new MySupplier());
        generate.limit(10).forEach(System.out::println);
    }
}

class MySupplier implements Supplier<Integer>{

    Integer temp = 0;

    @Override
    public  Integer get() {
        return temp++;
    }
}
