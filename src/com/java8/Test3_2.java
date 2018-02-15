package com.java8;


public class Test3_2 {

    public static void main(String[] args) {
        int I, N=20;

//        for (I=0; I<N; --I){ // бесконечный-неограниченный цыкл
//            System.out.print("*");
//        }

        System.out.println();

        for (I=0; -I<N; --I){ // только 20 прохождений
            System.out.print("*"); // 0 -1 -2 -3 -4 -5 -6 -7 -8 -9 -10 -11 -12 -13 -14 -15 -16 -17 -18 -19
        }

        System.out.println();
        N=20;

        for (I=0; I<N; --N){ // только 20 прохождений
            System.out.print("*"); // 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        }


//        for (I=0; I+N; --I){ // только 20 прохождений (C++)
//            System.out.print("*");
//        }

        for (I=0; I+N!=0; --I){ //for (I=0; C!=0; --I){ // только 20 прохождений (альтернатива C++)
            System.out.print("*"); // 0 -1 -2 -3 -4 -5 -6 -7 -8 -9 -10 -11 -12 -13 -14 -15 -16 -17 -18 -19 -20
        }

        System.out.println();
        A3 a3 = null;
        a3.func(); // Hello!

        System.out.println();

//        char[] arr = {'2','6','7'};
//        for (int c=arr.length; c>=0; --c){
//            System.out.print( arr[c-1] ); // Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: -1
//        }

        int A, B=99, C=99;

        A = B-- - --B;
        System.out.println( A ); // 2

//        B=99;
        A = --B - B--;
        System.out.println( A ); // 0

//        B=99;
        A = B-- - --C;
        System.out.println( A ); // 1 // -3

//        B=99; C=99;
        A = --B - C--;
        System.out.println( A ); // -1 // -5
    }

}


class A3 {
    public static void func(){
        System.out.println("Hello!");
    }
}
