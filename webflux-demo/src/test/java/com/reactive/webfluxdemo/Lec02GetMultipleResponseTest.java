package com.reactive.webfluxdemo;

import com.reactive.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Slf4j
class Lec02GetMultipleResponseTest extends BaseTest {

  @Autowired
  private WebClient webClient;

  @Test
  void fluxTest() {
    final Flux<Response> responseFlux = this.webClient
        .get()
        .uri("reactive-math/table/{number}", 5)
        .retrieve()
        .bodyToFlux(Response.class)
        .doOnNext(resp -> log.info(resp.toString()));

    StepVerifier.create(responseFlux)
        .expectNextCount(10)
        .verifyComplete();
  }

  @Test
  void fluxStreamTest() {
    final Flux<Response> responseFlux = this.webClient
        .get()
        .uri("reactive-math/table/{number}/stream", 5)
        .retrieve()
        .bodyToFlux(Response.class)
        .doOnNext(resp -> log.info(resp.toString()));

    StepVerifier.create(responseFlux)
        .expectNextCount(10)
        .verifyComplete();
  }
}
