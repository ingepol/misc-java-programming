package com.reactive.webfluxdemo.config;

import com.reactive.webfluxdemo.handler.CalculatorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class CalculatorRouterConfig {

  private final CalculatorHandler handler;

  @Bean
  public RouterFunction<ServerResponse> highLevelRouterCalculator() {
    return RouterFunctions.route()
        .path("calculator", this::serverResponseRouterFunction)
        .build();
  }


  private RouterFunction<ServerResponse> serverResponseRouterFunction() {
    final String path = "{first}/{second}";
    return RouterFunctions.route()
        .GET(path, isOperation("+"), handler::additionHandler)
        .GET(path, isOperation("-"), handler::subtractHandler)
        .GET(path, isOperation("*"), handler::multiplicationHandler)
        .GET(path, isOperation("/"), handler::divisionHandler)
        .GET(path, req -> ServerResponse.badRequest().bodyValue("OP Should be + - * /"))
        .build();
  }

  private RequestPredicate isOperation(final String operation) {
    return RequestPredicates.headers(
        headers -> operation.equals(headers.asHttpHeaders().toSingleValueMap().get("OP")));
  }


}
