//package com.java.task2;
//
//
//import com.sun.javafx.perf.PerformanceTracker;
//
//import java.io.PrintStream;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PerformanceReport {
//    private List<PerformanceTracker> trackerList = new ArrayList<>();
//
//    public void reset(int size) {
//        trackerList = new ArrayList<>(size);
//    }
//
//    public void track(PerformanceTracker performanceTracker) {
//        trackerList.add(performanceTracker);
//    }
//
//    public void printReport(PrintStream outputStream) {
//        for (PerformanceTracker tracker : trackerList) {
//            if (tracker != null ) {
//                outputStream.printf("%s %s : %.2f sec%n", tracker.getCollectionName(),
//                        tracker.getOperation(), tracker.getDuration() / 1000f);
//            } else {
//                outputStream.println("-------------------------------------");
//            }
//        }
//    }
//
//    public void addEmptyLine() {
//        trackerList.add(null);
//    }
//}
//
//
