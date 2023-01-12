package br.com.spring.webflux.reactive;


import io.reactivex.Flowable;

import java.util.Random;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PrintReactiveErrorFlow {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1,1, SECONDS)
                .map(PrintReactiveErrorFlow::transform)
                .subscribe(PrintReactiveErrorFlow::process,
                        PrintReactiveErrorFlow::dealWithError);
        SECONDS.sleep(10);
    }


    private static void dealWithError(Throwable throwable){
        throwable.printStackTrace();
    }

    private static void process(Long number){
        System.out.println(format("Receive number %s", number));
    }

    private static Long transform(Long number){
        if(new Random().nextDouble() < 0.3) throw new RuntimeException("Oops");
        return number * 2;
    }
}
