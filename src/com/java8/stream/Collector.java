package com.java8.stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Collector {

    public Collector() {
    }

    public Collector(String op) {
        System.out.println("New T object for op [" + op + "] has been created.");
    }


    public static void main(String[] args) {
//        Collector collector = new Collector();

        String[] emmiter = {"aaa", "bbb", "ccc", "ddd", "eee", "fff"};

        List<Collector> ops = Arrays.stream(emmiter)
                .map(Collector::new)
//                .map(val -> new Collector(val))
                .collect(Collectors.toList());
    }

}
