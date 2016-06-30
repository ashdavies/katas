package com.chaos.fibonacci;

import rx.Observable;
import rx.Subscriber;

public abstract class FibonacciGenerator {
    private static final long SLEEP = 50L;

    private final long limit;

    public FibonacciGenerator(long limit) {
        this.limit = limit;
    }

    public Observable<Long> generate() {
        return Observable.create(new Observable.OnSubscribe<Long>() {

            @Override
            public void call(final Subscriber<? super Long> subscriber) {
                int index = 1;
                Long result = 0L;

                do {
                    subscriber.onNext(result);
                    sleep(SLEEP);

                    result = calculate(index++);
                } while(result > 0 && result < limit);

                subscriber.onCompleted();
            }
        });
    }

    public abstract Long calculate(int number);

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException exception) {
            // Unneeded exception
        }
    }
}
