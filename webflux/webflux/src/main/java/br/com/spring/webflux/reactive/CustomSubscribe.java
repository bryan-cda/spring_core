package br.com.spring.webflux.reactive;

import java.util.concurrent.Flow;
import java.util.logging.Logger;

import static java.lang.String.format;

public class CustomSubscribe implements Flow.Subscriber<Integer>{
    private Logger logger = Logger.getAnonymousLogger();
    private Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(5);
    }

    @Override
    public void onNext(Integer number) {
        logger.info(format("Receiving %d", number));
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        logger.info("Complete!");
    }
}
