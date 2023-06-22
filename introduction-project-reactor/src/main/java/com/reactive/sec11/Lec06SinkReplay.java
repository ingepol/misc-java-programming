package com.reactive.sec11;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec06SinkReplay {

  public static void main(final String[] args) {
    // handle through which we would push items
    final Many<Object> sink = Sinks.many().replay().all();

    // handle through which subscribers will receive items
    final Flux<Object> flux = sink.asFlux();

    sink.tryEmitNext("hi");
    sink.tryEmitNext("How are you?");

    //When don't have subscriber, the old message would be consumed by the first subscriber.
    flux.subscribe(subscriber("sam"));
    flux.subscribe(subscriber("mike"));
    sink.tryEmitNext("?");

    flux.subscribe(subscriber("jake"));
    sink.tryEmitNext("new msg");
  }
}
