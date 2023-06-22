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
class Lec04HeaderTest extends BaseTest {

  @Autowired
  private WebClient webClient;

  @Test
  void headerTest() {
    final Mono<Response> responseMono = this.webClient
        .post()
        .uri("reactive-math/multiply")
        .bodyValue(buildRequest(5, 2))
        .headers(h -> h.set("someKey", "someVal"))
        .retrieve()
        .bodyToMono(Response.class)
        .doOnNext(resp -> log.info(resp.toString()));

    StepVerifier.create(responseMono)
        .expectNextCount(1)
        .verifyComplete();
  }

  @Test
  void headerAuthorizationTokenTest() {
    final Mono<Response> responseMono = this.webClient
        .post()
        .uri("reactive-math/multiply")
        .bodyValue(buildRequest(5, 2))
        .headers(h -> h.setBasicAuth("username", "password")) //not is the best option
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
