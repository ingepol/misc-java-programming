package com.reactive.sec12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec01Ctx {

  public static void main(final String[] args) {
    getWelcomeMessage()
        .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
        .contextWrite(Context.of("user", "sam"))
        .subscribe(subscriber());
  }

  private static Mono<String> getWelcomeMessage() {
    return Mono.deferContextual(ctx -> {
      if (ctx.hasKey("user")) {
        final String user = String.format("Welcome %s", ctx.get("user").toString());
        return Mono.just(user);
      } else {
        return Mono.error(new RuntimeException("unauthenticated"));
      }
    });
  }
}
