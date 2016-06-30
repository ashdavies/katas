package com.chaos.fibonacci;

import android.support.annotation.UiThread;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public abstract class FibonacciGenerator {
    protected static final long SLEEP = 50L;

    protected final Long limit;

    protected FibonacciGenerator(final Long limit) {
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

    @UiThread
    public abstract Long calculate(int number);

    protected void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException exception) {
            // Unneeded exception
        }
    }
}
