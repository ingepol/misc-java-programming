package com.reactive.sec01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Lec02MonoJust {

  public static void main(String[] args) {

    //Publisher
    Mono<Integer> mono = Mono.just(1);

    log.info(mono.toString());

    mono.subscribe(i -> log.info("Received: " + i));
  }

}
