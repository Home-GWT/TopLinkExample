package com.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 ** @see http://nicktt.github.io/java-day-omsk/#/step-1
 *       https://anadea.info/ru/blog/scala-tailrec-optimization
 *       (Хвостовая рекурсия в Scala) http://dchekmarev.ru/blog/article/1310195280
 *  @see http://javarevisited.blogspot.com/2016/03/difference-between-map-and-flatmap-in-java8.html
 ** @see https://habrahabr.ru/company/luxoft/blog/270383/
 ** @see (Java 8/Scala різниця в підходах та взаємні інновації) https://dou.ua/lenta/columns/java8-vs-scala
 *
 *
 */
public class Stream {

    /*
     * 'map()' - может преобразовывать каждый элемент стрима И возвращает только одно значение (а именно - коллекцию).
     * 'flatMap()' - может преобразовывать каждый элемент стрима НО возвращает поток значений (стрим).
     */

    public static void main(String[] args) {
        /**
         **  @see https://habrahabr.ru/post/265269 | https://habrahabr.ru/post/265583
         *** @see http://habrahabr.net/thread/15259
         *
         *   @see https://habrahabr.ru/post/209510
         * (Обзор языка программирования Scala) https://rsdn.org/article/philosophy/Scala.xml
         * (Новое в Java 8) http://microfork.com/whats-new-in-java-8
         * http://amadelio.ru/item/4935
         */
        List<String> values = Arrays.asList(new String[] {"a", "b", "c", "d", "e"});
        System.out.println(values);

        System.out.println();
        values.stream().forEach(value -> System.out.print(value + " "));

        values.stream()
                .map(h -> h.hashCode())
                .map(i -> Integer.toString(i));
    }

}
