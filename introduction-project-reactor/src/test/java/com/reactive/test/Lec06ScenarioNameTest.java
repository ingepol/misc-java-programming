package com.reactive.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

class Lec06ScenarioNameTest {

  @Test
  void test1() {
    final Flux<String> flux = Flux.just("a", "b", "c");
    final StepVerifierOptions scenarioName = StepVerifierOptions.create().scenarioName("alphabets-name");
    StepVerifier.create(flux, scenarioName)
        .expectNextCount(3)
        .verifyComplete();
  }

  @Test
  void test2() {
    final Flux<String> flux = Flux.just("a", "b", "c");
    StepVerifier.create(flux)
        .expectNext("a")
        .as("a-test")
        .expectNext("b")
        .as("b-test")
        .expectNext("c")
        .as("c-test")
        .verifyComplete();
  }

}
