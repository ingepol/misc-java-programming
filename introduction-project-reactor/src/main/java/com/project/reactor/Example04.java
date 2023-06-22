package com.project.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example04 {

    public static void main(String[] args) {
        Flux<String> flux = Flux.fromArray(new String[]{"data1", "data2", "data3"});
        log.info("Subscribe");
        flux.subscribe(log::info);
        log.info("Do On Next");
        flux.doOnNext(log::info).subscribe();
    }
}
