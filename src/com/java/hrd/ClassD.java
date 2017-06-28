package com.java.hrd;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @see http://echuprina.blogspot.com/2012/02/comparable-comparator.html
 *      https://habrahabr.ru/post/247015
 * @see https://javadevblog.com/primer-sortirovki-s-pomoshh-yu-java-comparable-i-comparator.html
 */

public class ClassD {

    public static void main(String[] args) {
        Set<DoubleHolder> doubleHolders = new TreeSet<>();
        doubleHolders.add(new DoubleHolder(100.0));
        doubleHolders.add(new DoubleHolder(99.9));
        doubleHolders.add(new DoubleHolder(111.1));
        doubleHolders.add(new DoubleHolder(0.0));
        System.out.println( doubleHolders );

        System.out.println();
        Set<Person> persons = new TreeSet<>(new Comparator() {
            @Override public int compare(Object o1, Object o2) {
                return ((Person)o1).ago > ((Person)o2).ago ? 1 : ((Person)o1).ago == ((Person)o2).ago ? 0 : -1;
            }
        });
        persons.add(new Person("Alex", 1));
        persons.add(new Person("Slava", 3));
        persons.add(new Person("Yra", 0));
        persons.add(new Person("Vladimir", 2));
        System.out.println( persons );



        // компаратор сортирует список или массив объектов по зарплате
        Comparator<Product> salaryComparator = new Comparator<Product>() {
            @Override public int compare(Product e1, Product e2) {
                return (int) (e1.getPrice() - e2.getPrice());
            }
        };

        System.out.println();
        Set<Product> products = new TreeSet<>();
        products.add(new Product("Ddd", 3.0, 1));
        products.add(new Product("Aaa", 1.0, 1));
        products.add(new Product("Ccc", 5.0, 1));
        products.add(new Product("Bbb", 4.0, 1));

//        Arrays.sort(products, salaryComparator);
        System.out.println( products );
    }

}


class DoubleHolder implements Comparable<DoubleHolder> {
    double d;

    public DoubleHolder(double d) {
        this.d = d;
    }

    @Override
    public int compareTo(DoubleHolder o) {
        return d > o.d ? 1 : d == o.d ? 0 : -1;
    }

    @Override
    public String toString() {
        return String.valueOf(d);
    }
}


class Person {

    public Person(String name, int ago){
        this.name = name;
        this.ago = ago;
    }

    String name;
    int ago;

    @Override
    public String toString() {
        return "{" +
                "'" + name + '\'' +
                ", " + ago +
                '}';
    }
}



// создадим простой объект, в котором будем хранить данные
class Product implements Comparable<Product> {
    private String name;
    private double price;
    private int quantity;

    public Product(){}
    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override public int compareTo(Product o) {
        return 1;
    }

    @Override public String toString() {
        return "{" +
                "'" + name + '\'' +
                ", " + price +
                ", " + quantity +
                '}';
    }
}


// теперь собственно реализуем интерфейс Comparator, для сортировки по названию
class SortedByName implements Comparator<Product> {

    public int compare(Product obj1, Product obj2) {
        String str1 = obj1.getName();
        String str2 = obj2.getName();
        return str1.compareTo(str2);
    }
}

// а так же реализуем интерфейс Comparator, для сортировки по цене
class SortedByPrice implements Comparator<Product> {

    public int compare(Product obj1, Product obj2) {
        double price1 = obj1.getPrice();
        double price2 = obj2.getPrice();

        if(price1 > price2) {
            return 1;
        }
        else if(price1 < price2) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
