package com.java.hrd;


public class ClassB {

    public static void main(String[] args) {
        int a = 0;
        String b = "";

        ClassB classB = new ClassB();

        System.out.println("a="+a+"; b='"+b+"';");
        classB.set1(a, b);
        System.out.println("a=" + a + "; b='" + b + "';");
        set2(a, b);
        System.out.println("a=" + a + "; b='" + b + "';");
        classB.i = 100;
        classB.str = "X";
        System.out.println("i="+classB.i+"; str='"+classB.str+"';");
        classB.set3(classB);
        System.out.println("i="+classB.i+"; str='"+classB.str+"';");
        classB.set4(classB);
        System.out.println("i="+classB.i+"; str='"+classB.str+"';");
    }

    public static void set1(int a, String b){
        a = 10;
        b = "Bbbbbbbbbb";
    }

    public static void set2(int a, String b){
        a = 10;
        b = "Bbbbbbbbbb";
    }

    public void set3(ClassB classB){
        classB.i = 10;
        classB.str = "Bbbbbbbbbb";
    }

    public void set4(ClassB classB){
        classB = null;
    }

    int i;
    String str;

}
