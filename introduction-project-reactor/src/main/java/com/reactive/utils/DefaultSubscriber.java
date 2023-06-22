package com.reactive.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import static java.util.Objects.isNull;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class DefaultSubscriber<T> implements Subscriber<T> {

  private String name;

  @Override
  public void onSubscribe(final Subscription subscription) {
    subscription.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(final T obj) {
    log.info("{} - Received: {}", this.getSubscriberName(), obj.toString());
  }

  @Override
  public void onError(final Throwable throwable) {
    log.error("{} - ERROR: {}", this.getSubscriberName(), throwable.getMessage());
  }

  @Override
  public void onComplete() {
    log.info("{} - Completed!", this.getSubscriberName());
  }

  private String getSubscriberName() {
    return isNull(this.name) ? "Subscriber" : this.name;
  }
}
