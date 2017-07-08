//package com.java.task2;
//
//import java.util.List;
//import java.util.Random;
//
//public class FillListTest extends ListTest {
//
//    public FillListTest(List listToTest) {
//        super(listToTest);
//    }
//
//    @Override
//    public void run() {
//        Random random = new Random();
//
//        for (int i = 0; i < LIST_LOAD_SIZE; i++) {
//            listToTest.add(0, Integer.toString(random.nextInt(ELEMENTS_RANGE)));
//        }
//    }
//}
