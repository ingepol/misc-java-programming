package com.reactive.sec04;

import reactor.core.publisher.Flux;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec02HandleAssignment {

  public static void main(final String[] args) {
    Flux.generate(sink -> sink.next(faker().country().name()))
        .map(Object::toString)
        .handle((str, sink) -> {
          sink.next(str);
          if (str.equalsIgnoreCase("canada")) {
            sink.complete();
          }
        }).subscribe(subscriber());
  }

}
