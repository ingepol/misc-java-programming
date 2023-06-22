package com.project.reactor;

import com.project.reactor.common.exception.ReactorException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Example03 {
    public static void main(final String[] args) {
        final Mono<String> mono = Mono.fromSupplier(() -> {
            throw new ReactorException("Exception");
        });

        mono.subscribe(
                log::info,
                err -> log.error(err.getMessage()),
                () -> log.info("Completed!")
        );
    }
}
