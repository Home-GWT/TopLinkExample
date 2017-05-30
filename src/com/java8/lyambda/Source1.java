package com.java8.lyambda;


public class Source1 {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    interface MyFace1 {
        void hello();
    }

    interface MyFace2 {
        void hello(String val);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    interface MyFace3 {
        void hello(String val);
    }

    static class MyClazz3 {
        public static void hello(String val) {
            System.out.println(val);
        }
    }


    public static void main(String[] args) {
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        MyFace1 face1 = () -> System.out.println("Hello!");
        face1.hello();

        MyFace2 face2 = val -> { System.out.println(val); };
        face2.hello("Hello!");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        MyFace3 face3 = MyClazz3::hello;
        face3.hello("Hello!");
    }

}
