package com.reactive.sec03;

import com.reactive.sec03.helper.NameProducer;
import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec08FluxPush {

  public static void main(final String[] args) {
    final NameProducer nameProducer = new NameProducer();
    //Push is not thread safe
    // If we have a single thread producer then take a look push
    // If it's used with multiple thread some of them could be lost
    Flux.push(nameProducer).subscribe(subscriber());
    final Runnable runnable = nameProducer::producer;
    for (int i = 0; i < 10; i++) {
      new Thread(runnable).start();
    }
    sleepSeconds(2);
  }
}
