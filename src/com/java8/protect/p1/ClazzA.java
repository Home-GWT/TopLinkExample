package com.java8.protect.p1;


public class ClazzA {

    public static void main(String[] args) {
        ClazzA a = new ClazzA();

        System.out.println("p1.ClazzA:\n----------");
        a.testA();
    }

    public void testA(){
        func();
        protectedFunc();
        privateFunc();
    }

    /*
     * модификатор по умолчанию - доступен внутри пакета: внутри классе-наследника и для вызова из объекта
     */
    void func(){
        System.out.println("Func");
    }

    /*
     * модификатор 'protected' ничего общего с наследованием не имеет
     */
    protected void protectedFunc(){
        System.out.println("Protected-Func");
    }

    /*
     * модификатор 'private' доступен только внутри класса
     */
    private void privateFunc(){
        System.out.println("Private-Func");
    }

}
