package com.reactive.sec11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec04SinkMulticast {

  public static void main(final String[] args) {
    // handle through which we would push items
    final Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();

    // handle through which subscribers will receive items
    final Flux<Object> flux = sink.asFlux();

    //flux.subscribe(subscriber("sam"));

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
