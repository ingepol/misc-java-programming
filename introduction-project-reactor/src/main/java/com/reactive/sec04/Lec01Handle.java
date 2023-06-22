package com.reactive.sec04;

import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec01Handle {

  public static void main(final String[] args) {

    //Handle = filter + map
    Flux.range(1, 20)
        .handle(((integer, synchronousSink) -> {
          if (integer % 2 == 0) {
            synchronousSink.next(integer);
          } else {
            synchronousSink.next(integer + "a");
          }
        })).subscribe(subscriber("filterMapHandle"));

    // Handle until condition
    Flux.range(1, 20)
        .handle(((integer, synchronousSink) -> {
          if (integer == 7) {
            synchronousSink.complete();
          } else {
            synchronousSink.next(integer);
          }
        })).subscribe(subscriber("handle"));

    Flux.range(1, 3)
        .map(i -> i / (2 - i))
        .onErrorReturn(3)
        .subscribe(subscriber());
  }

}
