package com.reactive.sec02;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;

@Slf4j
public class Lec06Subscription {

  public static void main(final String[] args) {
    final AtomicReference<Subscription> atomicReference = new AtomicReference<>();

    Flux.range(1, 20)
        .log()
        .subscribeWith(new Subscriber<Integer>() {
          @Override
          public void onSubscribe(final Subscription subscription) {
            log.info("Received Sub: " + subscription);
            atomicReference.set(subscription);
          }

          @Override
          public void onNext(final Integer integer) {
            log.info("onNext: {}", integer);
          }

          @Override
          public void onError(final Throwable throwable) {
            log.error("onError: {}", throwable.getMessage());
          }

          @Override
          public void onComplete() {
            log.info("onComplete");
          }
        });

    sleepSeconds(3);
    atomicReference.get().request(3);
    sleepSeconds(5);
    atomicReference.get().request(3);
    sleepSeconds(5);
    log.info("going to cancel");
    atomicReference.get().cancel();
    sleepSeconds(3);
    atomicReference.get().request(4);
    sleepSeconds(3);
  }
}
