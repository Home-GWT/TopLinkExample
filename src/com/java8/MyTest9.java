package com.java8;

import java.util.LinkedList;


/**
 * Стек - это структура данных, хранящая объекты в порядке "первым вошел, последним вышел" или "последним вошел, первым вышел".
 * Обычно у этой структуры есть API с такими методами, как push() и pop(). Иногда присутствует метод peek().
 *
 * Метод push() - принимает входной объект (например: foo и помещает его во внутренний контейнер; например: массив).
 * Метод push() обычно ничего не возвращает.
 *
 * Если передать методу push() два объекта, например, сначала foo, а потом bar - второй объект bar должен оказаться наверху концептуального стека, содержащего по крайней мере два объекта, так что при вызове метода pop() объект bar должен быть извлечен первым, до первого объекта foo. Если метод pop() вызвать еще раз, должен быть возвращен объект foo и стек должен стать пустым.
 *
 * Метод pop() удаляет самый последний элемент, добавленный в стек - да, метод pop() должен удалить верхний элемент, при этом предполагается, что в стеке есть элементы, чтобы их удалять.
 *
 * Метод peek() - работает точно также (метод pop()), но при этом объект не удаляется.
 * Метод peek() должен оставить верхний элемент в стеке.
 *
 * Если вызвать метод pop(), когда в стек еще ничего не было добавлено -  метод pop() должен выдать исключение, показывающее, что в стек еще ничего не добавлялось.
 *
 * Если выполнить команду push() null - стек должен выдать исключение, так как null не является допустимым значением для метода push().
 */
public class MyTest9 {

    public static void main(String[] args) {
        LinkedList<Integer> ll = null;

        System.out.println("\naddFirst+addLast");
        //////////////////////////////
        ll = new LinkedList<Integer>();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(null);
        System.out.println(ll);

        ll.addFirst(4);
        ll.addLast(5);
        System.out.println(ll);

        System.out.println("\npush+poll");
        //////////////////////////////
        ll = new LinkedList<Integer>();
        ll.push(1);
        ll.push(2);
        ll.push(3);
        ll.push(4);
        ll.push(null);
        System.out.println(ll);

        ll.poll();
        System.out.println(ll.poll());
        System.out.println(ll);

        System.out.println("\npop");
        //////////////////////////////
        ll = new LinkedList<Integer>();
        ll.push(1);
        ll.push(2);
        ll.push(3);
        ll.push(4);
        System.out.println(ll);

        ll.pop();
        System.out.println(ll.pop());
        System.out.println(ll);

        System.out.println("\npeek");
        //////////////////////////////
        ll = new LinkedList<Integer>();
        ll.push(1);
        ll.push(2);
        ll.push(3);
        ll.push(4);
        System.out.println(ll);

        ll.peek();
        System.out.println(ll.peek());
        System.out.println(ll);

        System.out.println("\nelement");
        //////////////////////////////
        ll = new LinkedList<Integer>();
        ll.push(1);
        ll.push(2);
        ll.push(3);
        ll.push(4);
        System.out.println(ll);

        System.out.println(ll.element());
    }

}
