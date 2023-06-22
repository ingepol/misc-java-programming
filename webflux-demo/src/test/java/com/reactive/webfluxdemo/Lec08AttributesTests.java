package com.reactive.webfluxdemo;

import com.reactive.webfluxdemo.dto.MultipleRequestDto;
import com.reactive.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
class Lec08AttributesTests extends BaseTest {

  @Autowired
  private WebClient webClient;

  @Test
  void basicAuthTests() {
    final Mono<Response> responseMono = this.webClient
        .post()
        .uri("reactive-math/multiply")
        .bodyValue(buildRequest(5, 2))
        .attribute("auth", "basic")
        .retrieve()
        .bodyToMono(Response.class)
        .doOnNext(resp -> log.info(resp.toString()));

    StepVerifier.create(responseMono)
        .expectNextCount(1)
        .verifyComplete();
  }

  @Test
  void basicOAuthTests() {
    final Mono<Response> responseMono = this.webClient
        .post()
        .uri("reactive-math/multiply")
        .bodyValue(buildRequest(5, 2))
        .attribute("auth", "oauth")
        .retrieve()
        .bodyToMono(Response.class)
        .doOnNext(resp -> log.info(resp.toString()));

    StepVerifier.create(responseMono)
        .expectNextCount(1)
        .verifyComplete();
  }

  @Test
  void withOutAuthTests() {
    final Mono<Response> responseMono = this.webClient
        .post()
        .uri("reactive-math/multiply")
        .bodyValue(buildRequest(5, 2))
        .retrieve()
        .bodyToMono(Response.class)
        .doOnNext(resp -> log.info(resp.toString()));

    StepVerifier.create(responseMono)
        .expectNextCount(1)
        .verifyComplete();
  }

  private MultipleRequestDto buildRequest(final int a, final int b) {
    final MultipleRequestDto requestDto = new MultipleRequestDto();
    requestDto.setFirst(a);
    requestDto.setSecond(b);
    return requestDto;
  }


}
