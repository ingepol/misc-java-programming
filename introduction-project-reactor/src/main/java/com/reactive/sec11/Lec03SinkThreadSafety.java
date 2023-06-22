package com.reactive.sec11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;

@Slf4j
public class Lec03SinkThreadSafety {

  public static void main(final String[] args) {
    // handle through which we would push items
    final Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
    // handle through which subscribers will receive items
    final Flux<Object> flux = sink.asFlux();

    final List<Object> list = new ArrayList<>();
    flux.subscribe(list::add);
    /*
    // This code don't emit all items
    for (int i = 0; i < 1000; i++) {
      final int j = i;
      CompletableFuture.runAsync(() -> sink.tryEmitNext(j));
    }*/
    for (int i = 0; i < 1000; i++) {
      final int j = i;
      CompletableFuture.runAsync(() -> sink.emitNext(j, (signalType, emitResult) -> true));
    }
    sleepSeconds(3);
    log.info("{}", list.size());
  }
}
