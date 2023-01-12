package br.com.spring.webflux.functional;

import java.util.stream.IntStream;

public class PrintFunctional {
    public static void main(String[] args) {
        IntStream.of(1,2,3)
                .forEach(System.out::println);
    }
}
