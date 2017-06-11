package com.java;


/**
 *  @see https://javadevblog.com/chto-takoe-pul-strok-v-java.html
 ** @see http://info.javarush.ru/translation/2014/02/07/Java-String-Вопросы-к-собеседованию-и-ответы-на-них-ч-2.html\
 *
 * 18. Что такое пул строк?
 * ***********************
 ** Когда мы используем двойные кавычки для создания строки, сначала ищется строка в пуле с таким же значением, если находится, то просто возвращается ссылка,
 *  иначе создается новая строка в пуле, а затем возвращается ссылка.
 ** Тем не менее, когда мы используем оператор new, мы принуждаем класс String создать новый объект строки, а затем мы можем использовать метод intern() для того,
 *  чтобы поместить строку в пул, или получить из пула ссылку на другой объект String с таким же значением.
 *
 * пул строк – это набор строк, который хранится в памяти Java heap.
 * Пул строк возможен исключительно благодаря неизменяемости строк в Java и реализации идеи интернирования строк.
 * Пул строк также является примером паттерна Приспособленец (Flyweight).
 * Пул строк помогает экономить большой объем памяти, но с другой стороны создание строки занимает больше времени.
 *
 * 19. Что делает метод intern()?
 * *****************************
 * Когда метод intern() вызван, если пул строк уже содержит строку, эквивалентную к нашему объекту, что подтверждается методом equals(Object), тогда возвращается ссылка
 * на строку из пула. В противном случае объект строки добавляется в пул и ссылка на этот объект возвращается.
 * Этот метод всегда возвращает строку, которая имеет то же значение, что что и текущая строка, но гарантирует что это будет строка из пула уникальных строк.
 *
 * 1. Concurrent Collections
 * *************************
 * @see https://habrahabr.ru/company/luxoft/blog/157273/
 *
 * CopyOnWrite коллекции:
 * - CopyOnWriteArrayList<E> — Потокобезопасный аналог ArrayList
 * - CopyOnWriteArraySet<E> — Имплементация интерфейса Set
 * Улучшенные реализации HashMap, TreeMap с лучшей поддержкой многопоточности и масштабируемости:
 * - ConcurrentMap<K, V> — Интерфейс, расширяющий Map
 * - ConcurrentHashMap<K, V> — В отличие от Hashtable и блоков synhronized на HashMap, данные представлены в виде сегментов, разбитых по hash'ам ключей
 * - ConcurrentNavigableMap<K,V> — Расширяет интерфейс NavigableMap и вынуждает использовать ConcurrentNavigableMap объекты в качестве возвращаемых значений
 * - ConcurrentSkipListMap<K, V> — Является аналогом TreeMap с поддержкой многопоточности
 * - ConcurrentSkipListSet<E> — Имплементация Set интерфейса
 */
public class StringPool {

    public static void main(String[] args) {
        String s1 = "Cat";
        String s2 = "Cat";
        String s3 = new String("Cat");
        // сравниваем наши строки
        System.out.println("s1 == s2 : " + ( s1 == s2 ) );
        System.out.println("s1 == s3 : " + ( s1 == s3 ) );
        System.out.println("s1 equals s3 : "+(s1.equals(s3)));

        System.out.println();
        //////////////////////////////////////////////////////
        String a = "string a";
        String b = new String("string a");
        String c = b.intern();

        System.out.println(a == b);
        System.out.println(b == c);
        System.out.println(a == c);


        System.out.println();
        //////////////////////////////////////////////////////
        String strA = new String("A");
        String strB = new String("A");
        String strC = strB.intern();
        System.out.println("strA == strB : " + (strA==strB));
        System.out.println("strA.equals(strB) : " + (strA.equals(strB)));
        System.out.println("strA == strC : " + (strA==strC));
        System.out.println("\"A\" == strC : " + ("A"==strC));
    }

}
