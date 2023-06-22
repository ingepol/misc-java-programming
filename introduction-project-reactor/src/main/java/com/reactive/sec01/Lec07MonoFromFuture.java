package com.reactive.sec01;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.onNext;
import static com.reactive.utils.ReactiveUtility.sleepSeconds;

public class Lec07MonoFromFuture {

  public static void main(final String[] args) {
    Mono.fromFuture(Lec07MonoFromFuture::getName).subscribe(onNext());
    sleepSeconds(1);
  }

  //CompletableFuture it's basically Gem Joa equivalent of JavaScript from Promises
  private static CompletableFuture<String> getName() {
    return CompletableFuture.supplyAsync(() -> faker().name().fullName());
  }

}
