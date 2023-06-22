package com.reactive.webfluxdemo.service;

import java.time.Duration;

import com.reactive.webfluxdemo.dto.MultipleRequestDto;
import com.reactive.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ReactiveMathService {

  public Mono<Response> findSquare(final int input) {
    return Mono.fromSupplier(() -> input * input)
        .map(Response::new);
  }

  public Flux<Response> multiplicationTable(final int input) {
    return Flux.range(1, 10)
        //.doOnNext(i -> sleepSeconds(1)) // it's not interrupted immediately 
        .delayElements(Duration.ofSeconds(1)) // Once subscribe cancel the process would be stopped
        .doOnNext(i -> log.info("reactive-math-service processing: {}", i))
        .map(i -> new Response(i * input));
  }

  public Mono<Response> multiply(final Mono<MultipleRequestDto> dtoMono) {
    return dtoMono
        .map(dto -> Math.multiplyExact(dto.getFirst(), dto.getSecond()))
        .map(Response::new);
  }

}
