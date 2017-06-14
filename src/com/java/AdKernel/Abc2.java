package com.java.AdKernel;


import java.util.LinkedList;
import java.util.List;

public class Abc2 {

    public static void main(String[] args) {
//        int b1 = -2147483648;
//        int b2 = 2147483647;
//        System.out.println( b1 );
//        System.out.println( b2 );
//
//        System.out.println( Integer.MIN_VALUE );
//        System.out.println( Integer.MAX_VALUE );

//        int hashCode = (int)((double)Math.random()*MAX_HASH);
//
//        System.out.println( hashCode );
//        System.out.println( hashFunc(hashCode) );

        Integer aaa = 100;
        System.out.println( aaa.hashCode() );
        System.out.println( Integer.hashCode((aaa.hashCode()*31)) );
        System.out.println( String.valueOf(aaa).hashCode() );
        System.out.println( String.valueOf(1).hashCode() );
        System.out.println( String.valueOf(100).hashCode() );
        System.out.println( String.valueOf(10000).hashCode() );

        System.out.println();

        Clazz3<Integer, Long> clazz = new Clazz3<>();
        clazz.put(1, 100l);
        clazz.put(2, 200l);
        clazz.put(3, 300l);
        clazz.toPrint();

    }

//    static int MAX_HASH = Integer.MAX_VALUE;
//    static int ARR_SIZE = 16;
//
//    static int hashFunc(final int hashCode){
//        return (int)((double)hashCode/MAX_HASH*ARR_SIZE);
//    }

}


class Clazz3<K,V> {

    final int MAX_HASH = Integer.MAX_VALUE;
    int ARR_SIZE = 16;

    List<Long>[] table;

    public Clazz3(){
        table = new LinkedList[ARR_SIZE];

        for(int i=0; i<ARR_SIZE; i++){
            table[i] = new LinkedList<>();
        }
    }

    private int hashFunc(final int hashCode){
        return (int)((double)hashCode/MAX_HASH*ARR_SIZE);
    }

    void put(K key, V val){
        System.out.println( key.hashCode() );
        table[ hashFunc(key.hashCode()) ].add( (Long)val );
    }

    void toPrint(){
        System.out.println();
        for (int i=0; i<table.length; i++){
            System.out.print(i + ") ");
            for (Long l : table[i]){
                System.out.print(l + " ");
            }
            System.out.println();
        }
    }

}