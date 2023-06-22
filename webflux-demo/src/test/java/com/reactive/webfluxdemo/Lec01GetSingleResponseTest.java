package com.reactive.webfluxdemo;

import com.reactive.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
class Lec01GetSingleResponseTest extends BaseTest {

  @Autowired
  private WebClient webClient;

  @Test
  void blockTest() {
    final Response block = this.webClient
        .get()
        .uri("/reactive-math/square/{input}", 5)
        .retrieve()
        .bodyToMono(Response.class) // Mono<Response>
        .block();
    assert block != null;
    log.info(block.toString());
  }

  @Test
  void stepVerifierTest() {
    final Mono<Response> responseMono = this.webClient
        .get()
        .uri("/reactive-math/square/{input}", 5)
        .retrieve()
        .bodyToMono(Response.class);// Mono<Response>
    StepVerifier.create(responseMono)
        .expectNextMatches(r -> r.getOutput() == 25)
        .verifyComplete();
  }

}
