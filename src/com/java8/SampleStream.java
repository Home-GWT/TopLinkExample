package com.java8;

import java.util.Arrays;
import java.util.Collection;

public class SampleStream {

    public static void main(String[] args) {
        String[] arr = {"q","w","e","r","t","y","u","uu","i","o","p","uuu","uuuu","ii","oo","pp","ooo","ppp"};
        Collection myCollection = Arrays.asList( arr );

        myCollection.stream()
                .filter( (item) -> item.toString().length()<=2)
                .distinct()
                .sorted()
                .map((item) -> item+" ")
                .forEach(System.out::print);
    }

}
