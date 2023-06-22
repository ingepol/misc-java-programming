package com.reactive.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

class Lec07CtxTest {

  @Test
  void test1() {
    StepVerifier.create(welcomeMessage())
        .verifyError(RuntimeException.class);
  }

  @Test
  void test2() {
    StepVerifierOptions stepVerifierOptions = StepVerifierOptions.create().withInitialContext(
        Context.of("user", "sam"));
    StepVerifier.create(welcomeMessage(), stepVerifierOptions)
        .expectNext("Welcome sam")
        .verifyComplete();
  }

  private Mono<String> welcomeMessage() {
    return Mono.deferContextual(ctx -> {
      if (ctx.hasKey("user")) {
        return Mono.just("Welcome " + ctx.get("user"));
      } else {
        return Mono.error(new RuntimeException("unauthenticated"));
      }
    });
  }

}
