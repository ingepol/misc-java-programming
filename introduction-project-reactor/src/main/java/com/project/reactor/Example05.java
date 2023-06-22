package com.project.reactor;

import com.project.reactor.common.subscirber.BackpressureSubscriber;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example05 {

    public static void main(final String[] args) {

        final Flux<Integer> range = Flux.range(1, 20)
                .doOnNext(i -> log.info(i.toString()));

        range
                .subscribe(
                        new BackpressureSubscriber<>()
                );

        range.
                limitRate(3)
                .subscribe();

    }

}


