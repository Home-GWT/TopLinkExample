package com.java8;


public class Test3 {

    public static void main(String[] args) {
        int I;
        int N = 20;

        System.out.println("1.");

//        for (I=0; I<N; --I) { // бесконечный цыкл
//            System.out.print(I);
//        }

        System.out.println("2.");

        for (I=0; -I<N; --I) { // 0 -1 -2 -3 -4 -5 -6 -7 -8 -9 -10 -11 -12 -13 -14 -15 -16 -17 -18 -19
            System.out.print(I);
        }
    }

}
