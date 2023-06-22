package com.reactive.sec11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.One;

import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec01SinkOne {

  public static void main(final String[] args) {
    // mono 1 value / empty /error 
    final One<Object> sink = Sinks.one();
    final Mono<Object> mono = sink.asMono();
    mono.subscribe(subscriber("sam"));
    mono.subscribe(subscriber("mike"));
    sink.tryEmitValue("hi!");
    //sink.tryEmitError(new RuntimeException());
    //the stream is ready close after emit 
    /*
      sink.emitValue("hi!", (signalType, emitResult) -> {
        log.info(signalType.name());
        log.info(emitResult.name());
        return false;
      });
  
      sink.emitValue("hello!", (signalType, emitResult) -> {
        log.info(signalType.name());
        log.info(emitResult.name());
        return true; // infinity loop, retry when error is happening
      });
    */

  }

}
