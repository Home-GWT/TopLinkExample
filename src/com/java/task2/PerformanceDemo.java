//package com.java.task2;
//
//
//import com.google.javascript.jscomp.PerformanceTracker;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Vector;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @see http://learn.javajoy.net/performance-arraylist-vector-linkedlist
// */
//
//public class PerformanceDemo {
//    private PerformanceReport performanceReport = new PerformanceReport();
//
//    public static final int LIST_LOAD_SIZE = 1_000_000;
//    public static final int LIST_GET_SIZE = 50_000;
//    public static final int LIST_REMOVE_SIZE = 10_000;
//    public static final int INSERT_SIZE = 10_000;
//    public static final int ELEMENTS_RANGE = LIST_LOAD_SIZE;
//
//    public static final String ARRAY_LIST_CLASS = "ArrayList";
//    public static final String LINKED_LIST_CLASS = "LinkedList";
//    public static final String VECTOR_CLASS = "Vector";
//
//    public static void main(String[] main) throws ExecutionException, InterruptedException {
//        PerformanceDemo demo = new PerformanceDemo();
//        demo.testAll();
//    }
//
//    private Callable<PerformanceTracker> buildTest(final String collectionName,
//                                                   final String operationName,
//                                                   final Runnable listTest) {
//        return new Callable<PerformanceTracker>() {
//            @Override
//            public PerformanceTracker call() {
//                final long startTime = System.currentTimeMillis();
//                listTest.run();
//                final long endTime = System.currentTimeMillis();
//                return new PerformanceTracker(collectionName, operationName, startTime, endTime);
//            }
//        };
//    }
//
//    private void testAll() throws ExecutionException, InterruptedException {
//        runListTest();
//        System.gc();
//        performanceReport.printReport(System.out);
//    }
//
//    private void runListTest() throws ExecutionException, InterruptedException {
//        int reportSize = 15;
//        performanceReport.reset(reportSize);
//        ExecutorService executor = Executors.newFixedThreadPool(reportSize);
//
//        ArrayList arrayList = new ArrayList();
//        Vector vector = new Vector();
//        LinkedList linkedList = new LinkedList();
//
//        String operation = String.format("Add %d elements to first", LIST_LOAD_SIZE);
//
//        performanceReport.track(executor.submit(buildTest(ARRAY_LIST_CLASS, operation, new FillListTest(new ArrayList<>()))).get());
//        performanceReport.track(executor.submit(buildTest(VECTOR_CLASS, operation, new FillListTest(new Vector<>()))).get());
//        performanceReport.track(executor.submit(buildTest(LINKED_LIST_CLASS, operation, new FillListTest(new LinkedList<>()))).get());
//        performanceReport.addEmptyLine();
//    }
//}
