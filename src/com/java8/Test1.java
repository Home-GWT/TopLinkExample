package com.java8;


public class Test1 {

    public static void main(String[] args) {
        new B();
    }

}


class A {
    private String str;

    public A(){
        str = "a";
        func();
    }

    protected void func(){
        System.out.println("A: " + str);
    }
}

class B extends A {
    private String str;

    public void func(){
        System.out.println("B: " + str);
    }
}
