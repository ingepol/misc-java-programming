package com.reactive.webfluxdemo.config;

import com.reactive.webfluxdemo.dto.InputFailedValidationResponse;
import com.reactive.webfluxdemo.exception.InputValidationException;
import com.reactive.webfluxdemo.handler.RequestHandler;
import java.util.function.BiFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

  private final RequestHandler requestHandler;


  @Bean
  public RouterFunction<ServerResponse> highLevelRouter() {
    return RouterFunctions.route()
        .path("router", this::serverResponseRouterFunction)
        .build();
  }


  private RouterFunction<ServerResponse> serverResponseRouterFunction() {
    return RouterFunctions.route()
        .GET("square/{input}", RequestPredicates.path("*/1?").or(RequestPredicates.path("*/20")),
            requestHandler::squareHandler)
        .GET("square/{input}", req -> ServerResponse.badRequest().bodyValue("only 10-20 allowed"))
        .GET("square/{input}/validation", requestHandler::squareHandlerWithValidation)
        .GET("table/{input}", requestHandler::tableHandler)
        .GET("table/{input}/stream", requestHandler::tableStreamHandler)
        .POST("multiply", requestHandler::multiplyHandler)
        .onError(InputValidationException.class, exceptionHandler())
        .build();
  }

  private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler() {
    return (err, req) -> {
      final InputValidationException ex = (InputValidationException) err;
      final InputFailedValidationResponse response = new InputFailedValidationResponse();
      response.setInput(ex.getInput());
      response.setErrorCode(ex.getErrorCode());
      response.setMessage(ex.getMessage());
      return ServerResponse.badRequest().bodyValue(response);
    };
  }


}
