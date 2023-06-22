package com.reactive.sec03;

import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec03FluxTake {

  public static void main(final String[] args) {
    Flux.range(1, 10)
        .take(3) //cancels the subscription when take 3 items
        .subscribe(subscriber());
  }
}
