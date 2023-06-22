package com.reactive.sec01;

import reactor.core.publisher.Mono;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.*;

public class Lec04MonoEmptyOrError {

  public static void main(final String[] args) {
    userRepository(2).subscribe(
        onNext(),
        onError(),
        onComplete()
    );
  }

  private static Mono<String> userRepository(final int userId) {
    if (userId == 1) {
      return Mono.just(faker().name().firstName());
    } else if (userId == 2) {
      return Mono.empty();
    } else {
      return Mono.error(new RuntimeException("Error: Not in the allowed range"));
    }
  }

}
