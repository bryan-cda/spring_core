package br.com.spring.webflux.reactive;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static io.reactivex.schedulers.Schedulers.computation;
import static java.lang.String.format;

public class BackPressure {
    public static void main(String[] args) {
        Flowable.create(BackPressure::emit,
//                BackpressureStrategy.BUFFER
//                BackpressureStrategy.DROP
//                BackpressureStrategy.ERROR
                BackpressureStrategy.LATEST
                )
                .observeOn(computation(), true, 2)
                .subscribe(BackPressure::process);

    }
    public  static void process(Integer number){
        System.out.println(format("Processing %d", number));
        sleep(1000L);
    }

    private static void emit(FlowableEmitter<Integer> emitter) {
        IntStream.rangeClosed(1,10)
                .forEach(n -> {
                    System.out.println(format("emitting %d",n));
                    emitter.onNext(n);
                    sleep();
                });
        emitter.onComplete();
    }

    public static void sleep(){
        try {
            TimeUnit.SECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(Long milliseconds){
        try {
            TimeUnit.SECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
