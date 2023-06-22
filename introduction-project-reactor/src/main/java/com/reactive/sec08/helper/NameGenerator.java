package com.reactive.sec08.helper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.sleepSeconds;

@Slf4j
public class NameGenerator {

  private final List<String> list = new ArrayList<>();

  public Flux<String> generateNames() {
    return Flux.generate(stringSynchronousSink -> {
          log.info("Generated fresh");
          sleepSeconds(1);
          final String name = faker().name().fullName();
          this.list.add(name);
          stringSynchronousSink.next(name);
        }).cast(String.class)
        .startWith(this.getFromCache());
  }

  private Flux<String> getFromCache() {
    return Flux.fromIterable(this.list);
  }

}
