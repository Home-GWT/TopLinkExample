package com.java8;


import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;

/**
 ** @see https://habrahabr.ru/post/216431/
 * @see https://habrahabr.ru/post/256057
 ** @see http://info.javarush.ru/translation/2014/10/09/Особенности-Java-8-максимальное-руководство-часть-2-.html
 *** @see https://examples.javacodegeeks.com/core-java/util/optional/java-8-optional-example/
 * @see https://habrahabr.ru/post/302628/
 **** @see http://javadevblog.com/polnoe-rukovodstvo-po-java-8-stream.html
 ** http://devcolibri.com/4274
 **** https://megahub.me/hub/java?w=16
 * *************************************
 * Optional — это контейнер объекта, который может содержать или не содержать ненулевое значение.
 * Этот объект имеет некоторые функции высшего порядка, избавляющие от добавления повторяющихся if null/notNull проверок, что позволяет нам сфокусироваться на том, что мы хотим сделать.
 */

public class Test8 {

    public static void main(String[] args) {
        Optional<String> optional = Optional.of("bam");
        System.out.println( optional.isPresent() );        // true
        System.out.println( optional.get() );              // "bam"
        System.out.println( optional.orElse("fallback") ); // "bam"

        System.out.println();

        Optional< String > fullName = Optional.ofNullable( null );
        System.out.println( "Full Name is set? " + fullName.isPresent() );
        System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) );
        System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );

        System.out.println();

        Optional< String > firstName = Optional.of( "Tom" );
        System.out.println( "First Name is set? " + firstName.isPresent() );
        System.out.println( "First Name: " + firstName.orElseGet( () -> "[none]" ) );
        System.out.println( firstName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );

        System.out.println();

//        Optional<Integer> opt = Optional.of(null); // Exception in thread "main" java.lang.NullPointerException
        Optional<Integer> opt = Optional.of(0);
        System.out.println("isPresent=" + opt.isPresent());
        System.out.println( "opt=" + opt.get() );

        /**
         * Пример № 1
         * Нахождение максимального и минимального значений
         */
        ArrayList<Integer> testValues = new ArrayList();
        testValues.add(0,15);
        testValues.add(1,1);
        testValues.add(2,2);
        testValues.add(3,100);
        testValues.add(4,50);

        Optional<Integer> maxValue = testValues.stream().max(Integer::compareTo);
        System.out.println("MaxValue="+maxValue);
        Optional<Integer> minValue = testValues.stream().min(Integer::compareTo);
        System.out.println("MinValue="+minValue);

        /*
         * 3. Создайте свои функции высшего порядка
         */
//        public void print(Consumer<Printer> toPrint) {
//
//            printer.prepare();
//            toPrint.accept(printer);
//            printer.sleep();
//        }

        /**
         * https://megahub.me/hub/java?w=16
         * ********************************
         * Класс Optional нельзя создать прямым образом через new. Он имеет приватный конструктор. Но для его создания существуют статические методы.
         */
    }

}
