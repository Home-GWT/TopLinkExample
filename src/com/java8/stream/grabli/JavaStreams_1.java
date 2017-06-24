package com.java8.stream.grabli;

import java.util.stream.IntStream;

/**
 * @see https://ruslan.ibragimov.by/26-11-2014.pervye-grabli-java-8
 *      ***********************************************************
 * Симулируем типичное веб приложение: к нам приходят пользователи и выполняют какие-то операции в параллельных потоках.
 * Без использования параллельных стримов мы получаем такие результаты:
 */

public class JavaStreams_1 {

    public static void main(String[] args) {
        test();
        test();
        test();
    }

    public static void test() {
        new Thread(() -> {
            long start = System.currentTimeMillis();

            long g = IntStream
                    .range(1, 100)
                    .map(x -> x * 2)
                    .sum();

            long stop = System.currentTimeMillis();
            System.out.println("Run1. Execution time: " + (stop-start) + "ms. Result: " + g);
        }).start();

        new Thread(() -> {
            long start = System.currentTimeMillis();

            long g = IntStream
                    .range(1, 100)
                    .map(x -> {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                        }
                        return 42;
                    })
                    .sum();

            long stop = System.currentTimeMillis();
            System.out.println("Run2. Execution time: " + (stop-start) + "ms. Result: " + g);
        }).start();
    }
}
