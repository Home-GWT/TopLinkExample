package com.java.AdKernel;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @see http://developer.alexanderklimov.ru/android/java/arraylist.php
 *      **************************************************************
 * Напишите простую, эффективную по памяти, реализацию алгоритма HashMap с открытой адресацией, для ключей типа int и значений типа long.
 * Для упрощения размер внутренней хеш-таблицы может быть фиксированным.
 * Реализуйте операции put, get, size.
 * Протестируйте правильность работы с помощью соответствующих Unit Tests.
 */

public class Abc {

    public static void main(String[] args) {
        HashMap<String, Integer> aaa = new HashMap<>();

        Clazz2<Integer, Long> clazz2 = new Clazz2<>();
    }


//    static int hashFunc(){
//        return 0;
//    }

    static int size = 16;

    static int myHashCode(){
        return 10101010;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

}

class Clazz2<K,V> {

    int size;
    List<K>[] table;

    public Clazz2(){}
    public Clazz2(int size){
        this.size = size;
        table = new LinkedList[size];

        for(int i=0; i<size; i++){
            table[i] = new LinkedList<>();
        }
    }

    void put(K key, V val){

    }
}
