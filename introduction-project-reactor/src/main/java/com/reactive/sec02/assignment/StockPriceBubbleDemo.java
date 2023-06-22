package com.reactive.sec02.assignment;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

import static com.reactive.utils.ReactiveUtility.onNext;

@Slf4j
public class StockPriceBubbleDemo {

  public static final int PRICE_BELOW = 90;

  public static final int PRICE_BEYOND = 110;

  public static void main(final String[] args) throws InterruptedException {
    final CountDownLatch latch = new CountDownLatch(1);
    final PriceBubble priceBubble = new PriceBubble(100);
    priceBubble.getPrice().subscribeWith(new Subscriber<Integer>() {
      private Subscription subscription;

      @Override
      public void onSubscribe(final Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(final Integer price) {
        log.info("{} Price: {}", LocalDateTime.now(), price);
        if (price > PRICE_BEYOND || price < PRICE_BELOW) {
          this.subscription.cancel();
          latch.countDown();
        }

      }

      @Override
      public void onError(final Throwable t) {
        latch.countDown();
      }

      @Override
      public void onComplete() {
        latch.countDown();
      }
    });
    Flux.range(3, 5)
        .map(i -> i / (i - 4)).subscribe(onNext());
    latch.await();


  }

}

