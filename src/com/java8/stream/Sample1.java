package com.java8.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;


/**
 * @see https://github.com/Home-Java8/Java8.2/tree/master/src/com/news/stream_api
 *      http://javahero.ru/java-8-пример-фильтрации-с-помощью-потоков/
 *      https://habrahabr.ru/post/302628/
 * @see http://microfork.com/whats-new-in-java-8/
 */

public class Sample1 {

    public static void main(String[] args) {
        SampleA a = new SampleA();
        String str = "1234567890,0987654321,6543217890,0987612345,0987654321,0987654321";

        //////////////////////////////////////////////////////////////////
        System.out.println( str );

        System.out.println();

        String[] arrStr = str.split(",");
        for (String str2 : arrStr)
            System.out.println( str2 );

        System.out.println();

        System.out.println( a.found("1234567890") );
        System.out.println( a.found("0987654321") );

        //////////////////////////////////////////////////////////////////
        System.out.println();

        Collection<String> collection = Arrays.asList(str.split(","));
        collection.stream()
                .forEach(System.out::println);

        System.out.println();

        boolean found1 = collection.stream()
                .anyMatch("0987654321"::equals); // Возвращает true если условие выполняется хотя бы для одного элемента
        System.out.println( found1 );

        System.out.println();

        String found2 = collection.stream() //Optional<Boolean> found2 = collection.stream()
                .filter(s -> "1234567890".equals(s))
                .findAny()
                .orElse("");
        System.out.println( "found2: " + found2 );
        String found3 = collection.stream() //Optional<Boolean> found2 = collection.stream()
                .filter(s -> a.found(s)!=null ? true:false) //.filter(s -> a::found!=null ? true : false ) //.filter(s -> { return a.found(s)!=null ? true : false; })
                .findAny()
                .orElse("NOT");
        System.out.println( "found3: " + found3 );

        //////////////////////////////////////////////////////////////////
//        Optional<String> found4 = Optional.of("NOT");
        Optional<String> found4 = collection.stream() //found4 = collection.stream()
                .filter(s -> a.found(s)!=null ? true:false)
                .findAny();
        System.out.println( found4 );
    }

}


class SampleA {

    public String found(String arg){
        return arg.equals("1234567890") ? arg : null;
    }

}
