package com.reactive.webfluxdemo.service;

import static com.reactive.webfluxdemo.service.SleepUtil.sleepSeconds;

import com.reactive.webfluxdemo.dto.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MathService {

  public Response findSquare(final int input) {
    return new Response(input * input);
  }

  public List<Response> multiplicationTable(final int input) {
    return IntStream.rangeClosed(1, 10)
        .peek(i -> sleepSeconds(1))
        .peek(i -> log.info("math-service processing: {}", i))
        .mapToObj(i -> new Response(i * input))
        .collect(Collectors.toList());
  }
  
}
