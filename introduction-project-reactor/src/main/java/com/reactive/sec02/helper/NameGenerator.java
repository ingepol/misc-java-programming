package com.reactive.sec02.helper;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.sleepSeconds;

public class NameGenerator {

  public static List<String> getNamesList(final int count) {
    final List<String> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(getName());
    }
    return list;
  }

  public static Flux<String> getNamesFlux(final int count) {
    return Flux.range(1, count)
        .map(i -> getName());
  }

  private static String getName() {
    sleepSeconds(1);
    return faker().name().fullName();
  }

}
