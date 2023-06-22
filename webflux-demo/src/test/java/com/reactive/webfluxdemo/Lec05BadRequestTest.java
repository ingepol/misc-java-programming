package com.reactive.webfluxdemo;

import com.reactive.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Slf4j
class Lec05BadRequestTest extends BaseTest {

  @Autowired
  private WebClient webClient;

  @Test
  void bedRequestTest() {
    final Flux<Response> responseMono = this.webClient
        .get()
        .uri("/reactive-math/square/{input}/throw", 5)
        .retrieve()
        .bodyToFlux(Response.class)
        .doOnNext(resp -> log.info(resp.toString()))
        .doOnError(err -> log.info(err.toString()));

    StepVerifier.create(responseMono)
        .verifyError(WebClientResponseException.BadRequest.class);
  }

}
