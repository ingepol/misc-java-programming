package com.reactive.sec04;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec03DoCallbacks {

  public static void main(final String[] args) {
    Flux.create(fluxSink -> {
          for (int i = 0; i < 5; i++) {
            fluxSink.next(i);
          }
          //fluxSink.complete();
          fluxSink.error(new RuntimeException("oops"));
          log.info("--completed");
        })
        .doOnComplete(() -> log.info("doOnComplete"))
        .doFirst(() -> log.info("doFirst"))
        .doOnNext(o -> log.info(String.format("doOnNext: %s", o.toString())))
        .doOnSubscribe(s -> log.info("doOnSubscribe: " + s))
        .doOnRequest(l -> log.info("doOnRequest: " + l))
        .doOnError(e -> log.error("doOnError: " + e.getMessage()))
        .doOnTerminate(() -> log.info("doOnTerminate"))
        .doOnCancel(() -> log.info("doOnCancel"))
        .doFinally(signalType -> log.info("doFinally: " + signalType))
        .doOnDiscard(Object.class, o -> log.info("doOnDiscard"))
        .take(2)
        .subscribe(subscriber());
  }
}
