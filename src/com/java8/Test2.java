package com.java8;


public class Test2 {

    public static void main(String[] args) {
        Test2 t2 = new Test2();

        C c = new C();
        c.num = 10;
        System.out.println(c.num);

        t2.func1(c);
        System.out.println(c.num);

        t2.func2(c);
        System.out.println(c.num);
    }

    public void func1(C c){
        c.num = 100;
    }

    public void func2(C c){
        c = null;
    }

}

class C {
    public Object o;
    public String str;
    public int num;
}
