package br.com.spring.webflux.reactive;

import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static java.lang.String.format;

public class CustomSubscribeTest {
    public static Logger logger = Logger.getAnonymousLogger();
    public static void main(String[] args) {

        CustomSubscribe customSubscribe = new CustomSubscribe();
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        publisher.subscribe(customSubscribe);
        IntStream.rangeClosed(1, 10)
                .forEach(number -> {
                    logger.info(format("Emitting %s", number));
                    publisher.submit(number);
                });
        sleep();
    }
    public static void sleep(){
        try {
            TimeUnit.SECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
