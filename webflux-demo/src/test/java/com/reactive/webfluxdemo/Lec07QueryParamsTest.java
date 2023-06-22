package com.reactive.webfluxdemo;

import java.net.URI;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Slf4j
class Lec07QueryParamsTest extends BaseTest {

  @Autowired
  private WebClient webClient;

  String queryString = "http://localhost:8080/jobs/search?count={count}&page={page}";

  @Test
  void queryParams() {
    final URI uri = UriComponentsBuilder.fromUriString(queryString)
        .build(10, 20);

    final Flux<Integer> integerFlux = this.webClient
        .get()
        .uri(uri)
        .retrieve()
        .bodyToFlux(Integer.class)
        .doOnNext(resp -> log.info(resp.toString()));

    StepVerifier.create(integerFlux)
        .expectNextCount(2)
        .verifyComplete();
  }

  @Test
  void queryParamsOtherApproach() {

    final Flux<Integer> integerFlux = this.webClient
        .get()
        .uri(b -> b.path("jobs/search").query("count={count}&page={page}").build(10, 20))
        .retrieve()
        .bodyToFlux(Integer.class)
        .doOnNext(resp -> log.info(resp.toString()));

    StepVerifier.create(integerFlux)
        .expectNextCount(2)
        .verifyComplete();
  }

  @Test
  void queryParamsOtherApproachWithMapOf() {

    final Map<String, Integer> params = Map.of("count", 10, "page", 20);
    final Flux<Integer> integerFlux = this.webClient
        .get()
        .uri(b -> b.path("jobs/search").query("count={count}&page={page}").build(params))
        .retrieve()
        .bodyToFlux(Integer.class)
        .doOnNext(resp -> log.info(resp.toString()));

    StepVerifier.create(integerFlux)
        .expectNextCount(2)
        .verifyComplete();
  }

}
