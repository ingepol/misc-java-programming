package com.reactive.sec11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

import java.time.Duration;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec05SinkMulticastDirectAll {

  public static void main(final String[] args) {

    System.setProperty("reactor.bufferSize.small", "16");

    // handle through which we would push items
    // Lose data from all subscribers if any of them are slow. It affects the performance 
    // of all of them. 
    //final Many<Object> sink = Sinks.many().multicast().directAllOrNothing();
    //Just lose data the subscriber that is slow
    final Many<Object> sink = Sinks.many().multicast().directBestEffort();

    // handle through which subscribers will receive items
    final Flux<Object> flux = sink.asFlux();

    flux.subscribe(subscriber("sam"));
    flux.delayElements(Duration.ofMillis(200)).subscribe(subscriber("mike"));

    for (int i = 0; i < 100; i++) {
      sink.tryEmitNext(i);
    }

    sleepSeconds(10);

  }
}
