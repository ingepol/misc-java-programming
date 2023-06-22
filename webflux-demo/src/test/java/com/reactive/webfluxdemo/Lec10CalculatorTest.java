package com.reactive.webfluxdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
class Lec10CalculatorTest extends BaseTest {

  private static final int A = 10;
  private static final String FORMAT = "%s %s %s = %s";

  @Autowired
  WebClient webClient;

  @Test
  void calculatorTest() {

    final Flux<String> flux = Flux.range(1, 5)
        .flatMap(b -> Flux.just("+", "-", "*", "/")
            .flatMap(op -> send(b, op)))
        .doOnNext(log::info);

    StepVerifier.create(flux)
        .expectNextCount(20)
        .verifyComplete();
  }

  private Mono<String> send(final int b, final String op) {
    return this.webClient
        .get()
        .uri("calculator/{a}/{b}", A, b)
        .headers(h -> h.set("OP", op))
        .retrieve()
        .bodyToMono(String.class)
        .map(v -> String.format(FORMAT, A, op, b, v));
  }
}
