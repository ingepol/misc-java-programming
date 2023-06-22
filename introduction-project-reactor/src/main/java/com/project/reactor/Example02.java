package com.project.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Example02 {

    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello");
        mono.subscribe(
                log::info,
                err -> log.error(err.getMessage()),
                () -> log.info("Completed!")
        );
    }
}
