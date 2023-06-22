package com.project.reactor;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Example01 {
    public static void main(final String[] args) {

        final List<Integer> elementFromMono = new ArrayList<>();
        final List<Integer> elementFromFlux = new ArrayList<>();

        // Crate a mono
        final Mono<Integer> mono = Mono.just(121);

        //Create a flux 
        final Flux<Integer> flux = Flux.just(12, 14, 9, 11, 12, 20, 23);

        //Subscribe mono
        mono.subscribe(elementFromMono::add);

        //Subscribe flux
        flux.subscribe(elementFromFlux::add);

        log.info(elementFromMono.toString());

        log.info(elementFromFlux.toString());

    }
}
