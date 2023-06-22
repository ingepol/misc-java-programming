package com.reactive.webfluxdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient webClient() {
    return WebClient.builder()
        .baseUrl("http://localhost:8080")
        // it's the best options to use a default authorization token
        //.defaultHeaders(h -> h.setBasicAuth("username", "password"))
        // The best way to generate authorization tokens dynamically
        .filter(this::sessionTokenWithAttributes)
        .build();
  }

  private Mono<ClientResponse> sessionToken(final ClientRequest request,
      final ExchangeFunction ex) {
    final ClientRequest clientRequest = ClientRequest.from(request)
        .headers(h -> h.setBearerAuth("some-lengthy-jwt")).build();
    return ex.exchange(clientRequest);
  }

  private Mono<ClientResponse> sessionTokenWithAttributes(final ClientRequest request,
      final ExchangeFunction ex) {
    //auth --> basic or auth
    final ClientRequest clientRequest = request.attribute("auth")
        .map(v -> v.equals("basic") ? withBasicAuth(request) : withOAuth(request))
        .orElse(request);
    return ex.exchange(clientRequest);
  }

  private ClientRequest withBasicAuth(final ClientRequest request) {
    return ClientRequest.from(request)
        .headers(h -> h.setBasicAuth("username", "password"))
        .build();
  }

  private ClientRequest withOAuth(final ClientRequest request) {
    return ClientRequest.from(request)
        .headers(h -> h.setBearerAuth("some-token"))
        .build();
  }
}
