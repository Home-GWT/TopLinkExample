package com.java8.protect.p2;


import com.java8.protect.p1.ClazzA;

public class ClazzC extends ClazzA {

    public static void main(String[] args) {
        ClazzA a = new ClazzA();

        System.out.println("\np2.ObjectA:\n----------");
//        a.func();          // За пределами пакета доступ к дефолтному методу объекта (НЕкласса) будет Закрыт
//        a.protectedFunc(); // За пределами пакета доступ к защищенному методу объекта (НЕкласса) будет Закрыт
////        a.privateFunc();

        System.out.println("\np2.ClazzC:\n----------");
        ClazzC c = new ClazzC();
        c.testC();
    }

    public void testC(){
//        func();            // За пределами пакета доступ к дефолтному методу в класса-наследника будет Закрыт
        protectedFunc();   // За пределами пакета доступ к защищенному методу класса-наследника будет Открыт
//        privateFunc();
    }

    /**
     * Пример как можно расширять доступ для модификаторов области видимости:
     * ***
     * то есть модификатор 'protected' (в отличии от метода по умолчанию) предназначен для расширения доступа для другого пакета из под класса-наследника
     */
    public void protectedFunc(){
        super.protectedFunc();
    }

}
