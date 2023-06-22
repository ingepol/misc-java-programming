package com.reactive.webfluxdemo.handler;

import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class CalculatorHandler {

  //creating multiple handlers intentionally
  // calculator/{first}/{second}
  public Mono<ServerResponse> additionHandler(final ServerRequest serverRequest) {
    return process(serverRequest, (a, b) -> ServerResponse.ok().bodyValue(a + b));
  }

  public Mono<ServerResponse> subtractHandler(final ServerRequest serverRequest) {
    return process(serverRequest, (a, b) -> ServerResponse.ok().bodyValue(a - b));
  }

  public Mono<ServerResponse> multiplicationHandler(final ServerRequest serverRequest) {
    return process(serverRequest, (a, b) -> ServerResponse.ok().bodyValue(a * b));
  }

  public Mono<ServerResponse> divisionHandler(final ServerRequest serverRequest) {
    return process(serverRequest, (a, b) -> b != 0 ? ServerResponse.ok().bodyValue(a / b) :
        ServerResponse.badRequest().bodyValue("divisor can not be 0"));
  }

  private Mono<ServerResponse> process(final ServerRequest request,
      final BiFunction<Integer, Integer, Mono<ServerResponse>> opLogic) {
    final int a = getValue(request, "first");
    final int b = getValue(request, "second");
    return opLogic.apply(a, b);
  }

  private static int getValue(final ServerRequest serverRequest, final String key) {
    return Integer.parseInt(serverRequest.pathVariable(key));
  }
}
