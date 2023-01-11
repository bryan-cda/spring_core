package br.com.spring.webflux.reactive;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class PrintReactive {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1,1, TimeUnit.SECONDS)
                .map(n -> n * 2)
                .subscribe(System.out::println);
                TimeUnit.SECONDS.sleep(5);
    }
}
