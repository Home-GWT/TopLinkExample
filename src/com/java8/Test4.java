package com.java8;


public class Test4 {

    public static void main(String[] args) {
        Test4 t4 = new Test4();

        System.out.println( t4.func1(5) );
        System.out.println( t4.func2(3, 5) );
        System.out.println( t4.func3(3,5) );
    }

    int func1(int step) {
        if (1<step)
            return func1(step - 1);
        else
            return step;
    }

    int func2(int step, int  val) {
        if (1<step)
            return val + func2(step-1, val);
        else
            return val;
    }

    int func3(int step, int val) {
        return 1<step ? val + func3(step-1, val) : val;
    }

}
