package com.java8.stream.grabli;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * Как запустить стрим в своем пуле?
 * Создать свой ForkJoinPool и отправить на выполнение таск с Stream:
 */

public class JavaStreams_3 {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
//        forkJoinPool.submit(() ->
//                        //parallel task here, for example
//                        IntStream.range(1, 1_000_000).parallel().filter(PrimesPrint::isPrime).collect(toList())
//        ).get();
    }

}
