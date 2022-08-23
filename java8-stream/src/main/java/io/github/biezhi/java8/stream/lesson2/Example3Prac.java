package io.github.biezhi.java8.stream.lesson2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Example3Prac {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");

        words.stream().map(word -> word.length()).
                collect(Collectors.toList()).forEach(i -> System.out.println(i));
    }

}
