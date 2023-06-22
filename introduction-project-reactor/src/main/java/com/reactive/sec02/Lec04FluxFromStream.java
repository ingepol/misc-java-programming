package com.reactive.sec02;

import reactor.core.publisher.Flux;

import java.util.List;

import static com.reactive.utils.ReactiveUtility.*;

public class Lec04FluxFromStream {

  public static void main(final String[] args) {
    final List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
    // Stream<Integer> stream = list.stream(); //Stream it's a one time use.
    // stream.forEach(i -> log.info(String.valueOf(i))); //Closed
    // stream.forEach(i -> log.info(String.valueOf(i)));
    // Flux<Integer> integerFlux = Flux.fromStream(() -> stream); // The stream would be closed.
    final Flux<Integer> integerFlux = Flux.fromStream(list::stream);

    integerFlux.subscribe(onNext(), onError(), onComplete());
    integerFlux.subscribe(onNext(), onError(), onComplete());
  }
}
