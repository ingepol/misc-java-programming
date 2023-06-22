package com.reactive.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class Lec02SVErrorTest {

  @Test
  void test1() {
    final Flux<Integer> just = Flux.just(1, 2, 3);
    final Flux<Integer> error = Flux.error(new RuntimeException("oops!"));
    final Flux<Integer> concat = Flux.concat(just, error);
    StepVerifier.create(concat)
        .expectNext(1)
        .expectNext(2)
        .expectNext(3)
        .verifyError();
  }

  @Test
  void test2() {
    final Flux<Integer> just = Flux.just(1, 2, 3);
    final Flux<Integer> error = Flux.error(new RuntimeException("oops!"));
    final Flux<Integer> concat = Flux.concat(just, error);
    StepVerifier.create(concat)
        .expectNext(1, 2, 3)
        .verifyError(RuntimeException.class);
  }

  @Test
  void test3() {
    final Flux<Integer> just = Flux.just(1, 2, 3);
    final Flux<Integer> error = Flux.error(new RuntimeException("oops!"));
    final Flux<Integer> concat = Flux.concat(just, error);
    StepVerifier.create(concat)
        .expectNext(1, 2, 3)
        .verifyErrorMessage("oops!");

  }

}
