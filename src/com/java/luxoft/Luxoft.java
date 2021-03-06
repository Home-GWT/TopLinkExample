package com.java.luxoft;

/**
 * A static{}
 * B static{}
 * A {}
 * B.func=null
 * B {}
 */

public class Luxoft {

    public static void main(String[] args) {
        B b = new B();
    }

}


class A {
    String str;

    /*
     * #1. первым делом, в классе, будут вызваны статические блоки И в базовом классе и потом в классе наследнике.
     *     (для всех бдлклв правило 'полиморфизма' не действует!)
     */
    static {
        System.out.println("A static{}");
    }

    /*
     * #2. Полиморфизм - это правило переопределение функции по умолчанию в классах наследниках действует только на функции класса.
     *     Те функции которые переопределенные в классе наследнике по умолчанию применяются для всех базовых классов.
     */
    void func(){
        System.out.println("A.func=" + str);
    }

    /*
     * #3. дальше, в классе, будут вызваны обычные блоки И в базовом классе и потом в классе наследнике.
     */
    {
        System.out.println("A {}");
    }

    /*
     * #4. только потом, в классе, будут вызваны конструкторы И в базовом классе и потом в классе наследнике.
     *     Конструкторы это такие себе именнованные блоки, которые ведут себя как обычные блоки, но при этом служат для иннициализации класса в момент его создания.
     */
    public A(){
        str = "A";
        func();
    }
}

class B extends A {
    String str;

    static {
        System.out.println("B static{}");
    }

    @Override
    void func(){
        System.out.println("B.func=" + str);
    }

    {
        System.out.println("B {}");
    }

    public B() {
        str = "B";
    }
}
