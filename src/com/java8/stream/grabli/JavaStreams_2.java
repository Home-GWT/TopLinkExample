package com.java8.stream.grabli;

import java.util.stream.IntStream;

/**
 * Давайте применим параллельные стримы:
 */

public class JavaStreams_2 {

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
                    .parallel()
                    .map(x -> x * 2)
                    .sum();

            long stop = System.currentTimeMillis();
            System.out.println("Run1. Execution time: " + (stop-start) + "ms. Result: " + g);
        }).start();

        new Thread(() -> {
            long start = System.currentTimeMillis();

            long g = IntStream
                    .range(1, 100)
                    .parallel()
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
