package com.reactive.webfluxdemo.controller;

import com.reactive.webfluxdemo.dto.MultipleRequestDto;
import com.reactive.webfluxdemo.dto.Response;
import com.reactive.webfluxdemo.service.ReactiveMathService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
@RequiredArgsConstructor
@Slf4j
public class ReactiveMathController {

  private final ReactiveMathService mathService;

  @GetMapping("square/{input}")
  public Mono<Response> findSquare(@PathVariable final int input) {
    return this.mathService.findSquare(input);
  }

  @GetMapping("table/{input}")
  public Flux<Response> multiplicationTable(@PathVariable final int input) {
    return this.mathService.multiplicationTable(input);
  }

  @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Response> multiplicationTableStream(@PathVariable final int input) {
    return this.mathService.multiplicationTable(input);
  }


  @PostMapping("multiply")
  public Mono<Response> multiple(@RequestBody final Mono<MultipleRequestDto> requestDtoMono,
      @RequestHeader final Map<String, String> headers) {
    log.info(headers.toString());
    return this.mathService.multiply(requestDtoMono);
  }


}
