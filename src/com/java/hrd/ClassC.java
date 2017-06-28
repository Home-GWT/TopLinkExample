package com.java.hrd;


public class ClassC {

    public static void main(String[] args) {
        int i = 1000000000;
        long l = 1000000000000000000l;
        System.out.println("i=" + i);
        System.out.println("l=" + l);

        System.out.println();
        l = i; // 1000000000
//        i = (int)l; // -1486618624
        System.out.println("i=" + i);
        System.out.println("l=" + l);
    }

}
