package com.reactive.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

import static java.util.Objects.isNull;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReactiveUtility {

  public static <T> Consumer<T> onNext() {
    return o -> log.info("Received: {}", o);
  }

  public static Consumer<Throwable> onError() {
    return ex -> log.error("Error: {}¸\n Cause: {}", ex.toString(),
        isNull(ex.getCause()) ? "Nothing" : ex.getCause().toString());
  }

  public static Runnable onComplete() {
    return () -> log.info("Completed!");
  }

  public static void sleepSeconds(final long seconds) {
    sleepMillis(seconds * 1000);
  }

  public static void sleepMillis(final long milli) {
    try {
      Thread.sleep(milli);
    } catch (final InterruptedException e) {
      log.error("It was an error while sleep thread was being executed!");
      Thread.currentThread().interrupt();
    }
  }

  public static <T> Subscriber<T> subscriber() {
    return new DefaultSubscriber<>();
  }

  public static <T> Subscriber<T> subscriber(final String name) {
    return new DefaultSubscriber<>(name);
  }

}
