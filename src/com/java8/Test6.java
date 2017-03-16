package com.java8;

import java.util.Arrays;
import java.util.Collection;


public class Test6 {

    public static void main(String[] args) {
//        String[] arr = {"q","w","e","r","t","y","u","uu","i","o","p",null,"uuu","uuuu","ii","oo","pp","ooo","ppp"};
//        Collection<String> collection = Arrays.asList(arr);
//
//        collection.stream()
//                .map((item) -> item + " ")
//                .sorted()
//                .filter((item) -> item.toString().length() <= 2)
//                .forEach(System.out::print);

        Collection<String> collection = Arrays.asList("q","w","e","r","t","y","u","uu","i","o","p",null,"uuu","uuuu","ii","oo","pp","ooo","ppp");

        collection.stream()
                .map((item) -> item + " ")
                .sorted()
                .filter((item) -> item.toString().length() <= 2)
                .forEach(System.out::print);

//        int sum = collection.stream()
//                .reduce(0,                                 // "reduce" принимает 3-значения: 1-ый – иннициализируем;
//                        (i, item) -> i += item.toString().length(), //   2-ой – аккумулятор (прибавляем к старому значению следующее значение);
//                        (sum1, sum2) -> sum1 + sum2); //   3-ий – фактически объедение (фиксируем резу
//        System.out.println("sum = " + sum);
    }

}
