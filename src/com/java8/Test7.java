package com.java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class Test7 {

    public static void main(String[] args) {
        Collection<Sports> sports = Arrays.asList(
                new Sports("Ivan", 5),
                new Sports(null, 15),
                new Sports("Petr", 7),
                new Sports("Ira", 10)
        );

        List<Sports> l1 = sports
                .parallelStream()
                .filter(sport -> sport.getName() != null
                        && sport.getName().startsWith("I"))
                .collect(Collectors.toList());

        System.out.println( l1.size() );


        String str = sports
                .stream()
                .filter(sport -> sport.getName() != null)
                .map(Sports::getName)
                .collect(Collectors.joining(" and ", "In camp ", " rest all days."));

        System.out.println( str );


        Integer integer = sports // Integer // 'int' -> Information:java: java.lang.NullPointerException
                .stream()
                .reduce(0,
                        (i, sport) -> i += sport.getDay(),
                        (sum1, sum2) -> sum1 + sum2);

        System.out.println(integer);
    }

}



class Sports {
    private String name;
    private Integer day;

    public Sports(String name, int day) {
        this.name = name;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
