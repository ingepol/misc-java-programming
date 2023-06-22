package com.reactive.webfluxdemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("jobs")
@Slf4j
@RequiredArgsConstructor
public class ParamsController {

  @GetMapping("search")
  public Flux<Integer> searchJobs(@RequestParam("count") final int count,
      @RequestParam("page") final int page) {
    return Flux.just(count, page);
  }
}
