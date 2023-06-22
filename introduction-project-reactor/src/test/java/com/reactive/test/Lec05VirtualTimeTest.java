package com.reactive.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

class Lec05VirtualTimeTest {

  @Test
  void test1() {
    StepVerifier.withVirtualTime(this::timeConsumingFlux)
        .thenAwait(Duration.ofSeconds(30))
        .expectNext("1a", "2a", "3a", "4a")
        .verifyComplete();
  }

  @Test
  void test2() {
    StepVerifier.withVirtualTime(this::timeConsumingFlux)
        .expectSubscription()
        .expectNoEvent(Duration.ofSeconds(4))
        .thenAwait(Duration.ofSeconds(20))
        .expectNext("1a", "2a", "3a", "4a")
        .verifyComplete();
  }

  private Flux<String> timeConsumingFlux() {
    return Flux.range(1, 4)
        .delayElements(Duration.ofSeconds(5))
        .map(i -> i + "a");
  }
}
