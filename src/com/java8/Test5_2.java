package com.java8;


import java.util.HashSet;

public class Test5_2 {

    public static void main(String[] args) {
        Clazzes1 clazzes1 = new Clazzes1();
        clazzes1.add(new Clazz("A"));
        clazzes1.add(new Clazz("B"));
        clazzes1.add(new Clazz("C"));
        for (Object o : clazzes1.toArray()){
            Clazz clazz = (Clazz)o;
            System.out.println(clazz);
        }

        System.out.println();

        Clazzes2 clazzes2 = new Clazzes2();
        clazzes2.add(new Clazz("A"));
        clazzes2.add(new Clazz("B"));
        clazzes2.add(new Clazz("C"));
        for (Object o : clazzes2.toArray()){
            Clazz clazz = (Clazz)o;
            System.out.println(clazz);
        }

        System.out.println();

        Clazzes3 clazzes3 = new Clazzes3();
        clazzes3.add(new Clazz("A"));
        clazzes3.add(new Clazz("B"));
        clazzes3.add(new Clazz("C"));
        for (Object o : clazzes3.toArray()){
            Clazz clazz = (Clazz)o;
            System.out.println(clazz);
        }
    }

//    public static void main(String[] args) {
//        Clazzes1 clazzes1 = new Clazzes1();
//        clazzes1.add(new Clazz("A"));
//        clazzes1.add(new Clazz("B"));
//        clazzes1.add(new Clazz("C"));
//        for (Object o : clazzes1.toArray()){
//            Clazz clazz = (Clazz)o;
//            System.out.println(clazz);
//        }
//
//        System.out.println();
//
//        Clazzes2 clazzes2 = new Clazzes2();
//        clazzes2.add(new Clazz("A"));
//        clazzes2.add(new Clazz("B"));
//        clazzes2.add(new Clazz("C"));
//        for (Object o : clazzes2){ //for (Object o : clazzes2.toArray()){
//            Clazz clazz = (Clazz)o;
//            System.out.println(clazz);
//        }
//
//        System.out.println();
//
//        Clazzes3 clazzes3 = new Clazzes3();
//        clazzes3.add("A"); //clazzes3.add(new Clazz("A"));
//        clazzes3.add("B"); //clazzes3.add(new Clazz("B"));
//        clazzes3.add("C"); //clazzes3.add(new Clazz("C"));
//        for (Object o : clazzes3){ //for (Object o : clazzes3.toArray()){
//            String clazz = (String)o; //Clazz clazz = (Clazz)o;
//            System.out.println(clazz);
//        }
//    }

}


class Clazz {
    private String name;

    public Clazz(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "name=" + name +
                '}';
    }
}

class Clazzes1 extends HashSet<Clazz> {
}

class Clazzes2 extends HashSet<Clazz> {
    public Clazz[] toArray() {
        return toArray();
    }
}

class Clazzes3<Clazz> extends HashSet {
    public Clazz[] toArray() {
        return toArray();
    }
}
