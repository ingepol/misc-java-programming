package com.reactive.sec03.helper.com.reactor.sec03;

import com.reactive.sec03.helper.NameProducer;
import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec02FluxCreateRefactoring {

  public static void main(final String[] args) {
    final NameProducer nameProducer = new NameProducer();
    Flux.create(nameProducer).subscribe(subscriber());
    final Runnable runnable = nameProducer::producer;
    for (int i = 0; i < 10; i++) {
      new Thread(runnable).start();
    }
  }
}
