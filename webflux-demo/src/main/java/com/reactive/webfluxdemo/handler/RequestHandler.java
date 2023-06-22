package com.reactive.webfluxdemo.handler;

import com.reactive.webfluxdemo.dto.MultipleRequestDto;
import com.reactive.webfluxdemo.dto.Response;
import com.reactive.webfluxdemo.exception.InputValidationException;
import com.reactive.webfluxdemo.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RequestHandler {

  private final ReactiveMathService mathService;

  public Mono<ServerResponse> squareHandler(final ServerRequest serverRequest) {
    final int input = Integer.parseInt(serverRequest.pathVariable("input"));
    final Mono<Response> responseMono = this.mathService.findSquare(input);
    return ServerResponse.ok().body(responseMono, Response.class);
  }

  public Mono<ServerResponse> tableHandler(final ServerRequest serverRequest) {
    final int input = Integer.parseInt(serverRequest.pathVariable("input"));
    final Flux<Response> responseFlux = this.mathService.multiplicationTable(input);
    return ServerResponse.ok().body(responseFlux, Response.class);
  }

  public Mono<ServerResponse> tableStreamHandler(final ServerRequest serverRequest) {
    final int input = Integer.parseInt(serverRequest.pathVariable("input"));
    final Flux<Response> responseFlux = this.mathService.multiplicationTable(input);
    return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
        .body(responseFlux, Response.class);
  }

  public Mono<ServerResponse> multiplyHandler(final ServerRequest serverRequest) {
    final Mono<MultipleRequestDto> multipleRequestDtoMono = serverRequest.bodyToMono(
        MultipleRequestDto.class);
    final Mono<Response> multiply = this.mathService.multiply(multipleRequestDtoMono);
    return ServerResponse.ok().body(multiply, Response.class);
  }

  public Mono<ServerResponse> squareHandlerWithValidation(final ServerRequest serverRequest) {
    final int input = Integer.parseInt(serverRequest.pathVariable("input"));
    if (input < 10 || input > 20) {
      return Mono.error(new InputValidationException(input));
    }
    final Mono<Response> responseMono = this.mathService.findSquare(input);
    return ServerResponse.ok().body(responseMono, Response.class);
  }


}
