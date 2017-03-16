package com.java8.protect.p1;


public class ClazzB extends ClazzA {

    public static void main(String[] args) {
        ClazzA a = new ClazzA();

        System.out.println("p1.ObjectA:\n----------");
        a.func();          // Внутри пакета доступ к дефолтному методу объекта (НЕклассу) будет Открыт
        a.protectedFunc(); // Внутри пакета доступ к защищенному методу объекта (НЕклассу) будет Открыт
//        a.privateFunc();

        System.out.println("\np1.ClazzB:\n----------");
        ClazzB b = new ClazzB();
        b.testB();
        System.out.println("\n(ClazzA) p1.ClazzB:\n----------");
        b.func();          // Внутри пакета доступ к дефолтному методу объекта (НЕклассу) будет Открыт
        b.protectedFunc(); // Внутри пакета доступ к защищенному методу объекта (НЕклассу) будет Открыт
    }

    public void testB(){
        func();          // Внутри пакета доступ к дефолтному методу класса-наследника будет Открыт
        protectedFunc(); // Внутри пакета доступ к защищенному методу класса-наследника будет Открыт
//        privateFunc();
    }

}

