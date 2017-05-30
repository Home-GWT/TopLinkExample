package com.java;


import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * https://www.otstavnov.com/Odersky_Scala_lecture-notes.html
 * https://itnan.ru/post.php?c=1&p=319282
 * http://ru.tmsoftstudio.com/file/page/intro-java/week9-lec35.html
 * https://ru.wikipedia.org/wiki/Функция_высшего_порядка
 * https://ru.wikibooks.org/wiki/Реализации_алгоритмов/Замыкание
 * https://ru.wikipedia.org/wiki/Каррирование
 */
public class Forbes {

    ///////////////////////////////////////////////////////////////////////////
    /*
     * Замыкание - ( является глобальной переменной ) в теле функции присутствуют ссылки на переменные которые объявленные вне тела этой функции.
     */

    ///////////////////////////////////////////////////////////////////////////
    /*
     * Каррирование - это преобразование функции со многими аргументами в набор функций каждая из которых имеет по одному аргументу.
     */

    public static void main(String[] args) {
        System.out.println( func(3) );  // обычная рекурсия
        System.out.println( add(3,3) ); // хвостовая рекурсия

        ///////////////////////////////////////////////////////////////////////////
        /*
         * Функции высшего порядка - это функция которая принимает в качестве аргументов другие функции или возвращающая другую функцию в качестве результата.
         * ( Идея состоит в том что функции имеют такой-же статус как и другие объекты данных.
         *   Использование функций высшего порядка приводит к абстрактным и компактным программам... )
         */
        Function<Integer, Integer> f = x -> x + 3;
        BiFunction<Function<Integer, Integer>, Integer, Integer> g = (func, x) -> func.apply(x) * func.apply(x);
        System.out.println(g.apply(f, 7));
    }

    ///////////////////////////////////////////////////////////////////////////
    /*
     * При обычной рекурсии - в теле функции остается лежать текущее значение переменной.
     * И каждый раз когда функция вызывает саму-себя - клонируется тело этой функции, но уже с другим значением переменной.
     * И таких клонов может быть безконечное количество - именно это услоие (наличие безконечного количества клонов) и приводит к переполнение стэка.
     */
    static int func(int n){
        if(n==0) return 0;
        return n + func(n-1); //return n+func(--n); //return n+func(n--);
    }

    ///////////////////////////////////////////////////////////////////////////
    /*
     * В хвостовой рекурсии - в теле функции никакие переменные вообще не храняться!
     * Все значения для переменных передаются только в параметрах функции.
     * В теле самой-же функции лежит только оператор-возврата значения. А все значения вычисляются на уровне, в момент передачи параметров.
     * То есть, каждый раз когда функция вызывает саму-себя старое тело функции уничтожается! И поэтому клон функции будет всегда один (это значит что переполнения стэка никогда не наступит)
     */
    static int add(int nun, int step){
        if(step==0) return nun; //return 1;
        return add((nun+(step-1)), step-1);
    }

}