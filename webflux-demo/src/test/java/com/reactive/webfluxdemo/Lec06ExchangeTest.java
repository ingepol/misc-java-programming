package com.reactive.webfluxdemo;

import com.reactive.webfluxdemo.dto.InputFailedValidationResponse;
import com.reactive.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
class Lec06ExchangeTest extends BaseTest {

  @Autowired
  private WebClient webClient;

  //exchange = retrieve + additional info http status code
  @Test
  void bedRequestExchangeTest() {
    final Mono<Object> responseMono = this.webClient
        .get()
        .uri("/reactive-math/square/{input}/throw", 5)
        .exchangeToMono(this::exchange)
        .doOnNext(resp -> log.info(resp.toString()))
        .doOnError(err -> log.info(err.toString()));

    StepVerifier.create(responseMono)
        .expectNextCount(1)
        .verifyComplete();
  }

  private Mono<Object> exchange(final ClientResponse cr) {
    if (cr.rawStatusCode() == 400) {
      return cr.bodyToMono(InputFailedValidationResponse.class);
    } else {
      return cr.bodyToMono(Response.class);
    }
  }


}
