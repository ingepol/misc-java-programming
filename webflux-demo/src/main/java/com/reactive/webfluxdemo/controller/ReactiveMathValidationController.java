package com.reactive.webfluxdemo.controller;

import com.reactive.webfluxdemo.dto.Response;
import com.reactive.webfluxdemo.exception.InputValidationException;
import com.reactive.webfluxdemo.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
@RequiredArgsConstructor
@Slf4j
public class ReactiveMathValidationController {

  private final ReactiveMathService mathService;

  @GetMapping("square/{input}/throw")
  public Mono<Response> findSquare(@PathVariable final int input) {
    if (input < 10 || input > 20) {
      throw new InputValidationException(input);
    }
    return this.mathService.findSquare(input);
  }

  @GetMapping("square/{input}/mono-error")
  public Mono<Response> monoError(@PathVariable final int input) {
    return Mono.just(input).
        handle((num, sink) -> {
          if (num >= 10 && num <= 20) {
            sink.next(num);
          } else {
            sink.error(new InputValidationException(num));
          }
        })
        .cast(Integer.class)
        .flatMap(this.mathService::findSquare);

  }

  @GetMapping("square/{input}/assignment")
  public Mono<ResponseEntity<Response>> assignment(@PathVariable final int input) {
    return Mono.just(input).
        filter(num -> num >= 10 && num <= 20)
        .flatMap(this.mathService::findSquare)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.badRequest().build());
  }

}
