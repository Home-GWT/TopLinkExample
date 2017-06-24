package com.java.luxoft;

/**
 *
 */
public class Luxoft2 {

    public static void main(String[] args) {
        A1.func("1");
        A2.func("2");
    }


    static class A1 {
        public static void func(String arg) {
            System.out.println("A1="+arg);
        }
    }

    static class A2 extends A1 {
        public static void func(String arg) {
            System.out.println("A2="+arg);
        }
    }

}
