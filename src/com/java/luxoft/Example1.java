package com.java.luxoft;


import java.util.Date;

public class Example1 {

    public static void main(String[] args) {
        Example1 exam = new Example1();

        Test test = new Test(1);
//        Test2 test2 = new Test2(1);
        System.out.println( test );

        System.out.println();
        exam.func1(test);
//        exam.func1(test2);
        System.out.println( test );

        System.out.println();
        exam.func3(test);
        System.out.println( test );

        System.out.println();
        char[] ch = {'S','a','s','h','a'};
//        My1 my = new My1(100, new char[]{'S','a','s','h','a'}, 10000l);
        new My1(39, new char[]{'S','a','s','h','a'}, 10000l);
        System.out.println("num="+My1.getNum() + "; name="+My1.getName() + "; date="+My1.getDate() + ";");
        My1 my = new My1(4, new char[]{'A','l','e','s','y','a'}, 10000l);
        System.out.println("num="+My1.getNum() + "; name="+My1.getName() + "; date="+My1.getDate() + ";");
        System.out.println("num="+my.getNum() + "; name="+my.getName() + "; date="+my.getDate() + ";");
    }


    void func1 (Test test){
        test.num = 10;
    }

    void func1 (Test2 test){
//        test.num = 10; // Ощибка компиляции, потому-что поле иммутабл
    }

    void func2 (Test test){
        test = null;
    }

    void func3 (final Test test){
//        test = null; // Ошибка компиляции, потому-что ссылку менять нельзя...
//        test = new Test(100); // Ошибка компиляции, потому-что ссылку менять нельзя...
        test.num = 100;
    }
}


class Test {
    Test(int num){
        this.num = num;
    }
    int num;

    @Override public String toString(){
        return "Test{num=" + num + "}";
    }
}

class Test2 {
    Test2(int num){
        this.num = num;
    }
    final int num;

    @Override public String toString(){
        return "Test{num=" + num + "}";
    }
}


final class My1 implements Cloneable {

    public My1(final int num, final char[] name, final long time){
        this.num = num;
        this.name = new String(name);
        this.date = new Date( time );
    }

    private static int num;
    private static String name;
    private static Date date;

    public static int getNum(){
        return num;
    }

    public static String getName(){
        return name;
    }

    public static Date getDate(){
        return date;
    }

    @Override
    public My1 clone(){
        date = (Date)date.clone();
        return this;
    }

    @Override
    public String toString() {
        return "My1{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}


//class My2 extends My1 {
//
//}

//class My3 extends String {
//
//}