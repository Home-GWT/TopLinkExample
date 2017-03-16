package com.java8;


import org.omg.CORBA.*;

import java.lang.Object;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test5 {

    public static void main(String[] args) {
        Addresses0 addresses0 = new Addresses0();
        addresses0.add(new Address(100,100));
        addresses0.add(new Address(200,200));
        addresses0.add(new Address(300,300));

        for (Object o : addresses0){
            Address address = (Address)o;
            System.out.println(address);
        }


        Addresses1 addresses1 = new Addresses1();
        addresses1.add(new Address(100,100));
        addresses1.add(new Address(200,200));
        addresses1.add(new Address(300,300));

        for (Address address : addresses1){
            System.out.println(address);
        }


        Addresses2 addresses2 = new Addresses2();
        addresses2.put("A", "a");
        addresses2.put("B", "b");
        addresses2.put("C", "c");

        for (Map.Entry<String,String> address : addresses2.entrySet()){
            System.out.println(address);
        }


//        for (Address address : (Address[])addresses.toArray()){
//            System.out.println(address);
//        }
        Addresses3 addresses3 = new Addresses3();
        addresses3.add(new Address(100,100));
        addresses3.add(new Address(200,200));
        addresses3.add(new Address(300,300));

        for (Object o : addresses3.toArray()){
            Address address = (Address)o;
            System.out.println(address);
        }

        ////////////////////////////////////////////////////////////
        Addresses4 addresses4 = new Addresses4();
        addresses4.add(new Address(100,100));
        addresses4.add(new Address(200,200));
        addresses4.add(new Address(300,300));
        for (Object o : addresses4){ //for (Object o : addresses4.toArray()){
            Address address = (Address)o;
            System.out.println("4: " + address);
        }

        Addresses5 addresses5 = new Addresses5();
        addresses5.add(new Address(100,100));
        addresses5.add(new Address(200,200));
        addresses5.add(new Address(300,300));
        for (Object o : addresses5){ //for (Object o : addresses5.toArray()){
            Address address = (Address)o;
            System.out.println("5: " + address);
        }

    }

}


class Address {
    private int longitude;
    private int latitude;

    public Address(){}

    public Address(int longitude, int latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Address{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}

class Addresses0 extends HashSet<Address> {
}

class Addresses1 extends HashSet<Address> {
    public Address[] toArray() {
        return toArray(new Address[size()]);
    }
}


//class Addresses<Address> extends HashSet {
//
//}

class Addresses2 extends HashMap<String,String> {
}

class Addresses3<Address> extends HashSet {
    public Address[] toArray() {
        return (Address[]) super.toArray();
    }
}


class Addresses4 extends HashSet<Address> {
    public Address[] toArray() {
        return toArray();
    }
}

class Addresses5<Address> extends HashSet {
    public Address[] toArray() {
        return toArray();
    }
}

