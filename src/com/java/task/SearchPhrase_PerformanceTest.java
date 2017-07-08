package com.java.task;


/**
 * @see https://ru.stackoverflow.com/questions/186245/
 *      https://habrahabr.ru/post/233797/
 *      https://habrahabr.ru/post/132241/
 */

public class SearchPhrase_PerformanceTest {

    private static final long MEGABYTE = 1024L * 1024L,
            KILOBYTE = 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public static long bytesToKilobytes(long bytes) {
        return bytes / KILOBYTE;
    }

    static Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        ////////////////////////////////////
//        SearchPhrase example = new SearchPhrase();
//        example.askUserPathAndWord();

//        int a = 100;
//        Integer aa = new Integer(100);
        Integer[] aa = new Integer[1000];
        ////////////////////////////////////

        // Get the Java runtime
//        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();

//        if( memory<KILOBYTE )
            System.out.println("Used memory: " + memory + " (b)");
//        else if( memory<MEGABYTE )
//            System.out.println("Used memory: " + bytesToKilobytes(memory) + " (Kb)");
//        else
//            System.out.println("Used memory: " + bytesToMegabytes(memory) + " (Mb)");
    }
}
