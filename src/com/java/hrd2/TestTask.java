package com.java.hrd2;

import java.util.*;
import java.util.function.Predicate;

/**
 * Существует таблица A, содержащая 2 поля (WORD, ID) и таблица B, содержащие (WORD, ID2)
 * IDs случайны и не совпадают.   |   UUID.randomUUID()
 * Можете ли вы создать и заполнить таблицу C, содержащую (ID),
 * Заполните только
 * 1. IDs из записей, которые присутствуют в таблице A, но нет в B,
 * 2. и записи, которые присутствуют в таблице B, но нет в A.
 * Поиск по WORD.
 * Пожалуйста решите это с помощью Java (без помощи SQL) и подсчитайте результаты.
 *
 * @see https://stackoverflow.com/questions/32708277/java-inbuilt-utility-for-generating-a-random-sequence-of-numbers
 *      https://stackoverflow.com/questions/12829197/java-generating-random-sequence-of-numbers-with-random-access-to-the-sequence-el
 */

public class TestTask {

    public static void main(String[] args) {
//        for (int i=0; i<33; i++){
//            String sequence = UUID.randomUUID().toString();
//            System.out.println(sequence);
//        }

        Map<Word, String> tableA = new HashMap<>();
        tableA.put(new Word("Sasha"), UUID.randomUUID().toString());
        tableA.put(new Word("Masha"), UUID.randomUUID().toString());
        tableA.put(new Word("Roman"), UUID.randomUUID().toString());
        System.out.println(tableA);

        Map<Word, String> tableB = new HashMap<>();
        tableB.put(new Word("Alexandr"), UUID.randomUUID().toString());
        tableB.put(new Word("Masha"), UUID.randomUUID().toString());
        tableB.put(new Word("Vladimir"), UUID.randomUUID().toString());
        System.out.println(tableB);

        System.out.println();
        Map<Word, String> table = new HashMap<>();
        table.putAll( tableA );
        table.putAll( tableB );
        System.out.println(table);

        System.out.println();
//        System.out.println( table );
//        System.out.println( tableA.entrySet() );
//        System.out.println( table.keySet() );
        table.keySet().removeAll(tableA.keySet());
        System.out.println( table );


        List<String> tableC = new ArrayList<>();
        tableC.addAll(table.values());
        System.out.println( tableC );
        System.out.println( tableC.size() );
    }

}


class Word {

    public Word(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * Если не переопределять 'equals' то объекты будут всегда разные
     * **************************************************************
     * То есть, в случае колизии в одном бекете будет несколько одинаковых объектов (как разные объекты)
     */
    @Override public boolean equals(Object that) {
        Word word = (Word) that;
        return name.equals(word.getName())
                ? true
                : false;
    }

    @Override public int hashCode() {
        return name != null
                ? name.hashCode()
                : 0;
    }

    @Override public String toString() {
        return "'" + name + "'";
    }
}