package io.javabrains.workshop;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
public class Exercise8 {


    public static void main(final String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens
        ReactiveSources.intNumbersFluxWithException()
                .doOnError(err -> log.error("Challenge 1 [ERROR]:" + err.getMessage()))
                .subscribe(num -> log.info("Challenge 1: " + num));

        // Print values from intNumbersFluxWithException and continue on errors
        ReactiveSources.intNumbersFluxWithException()
                .onErrorContinue((err, num) -> log.error("Challenge 2 " + num + "[ERROR]:" + err.getMessage()))
                .subscribe(num -> log.info("Challenge 2: " + num));


        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        ReactiveSources.intNumbersFluxWithException()
                .onErrorResume(err -> Flux.just(-1, -2))
                .subscribe(num -> log.info("Challenge 3: " + num));

        log.info("Press a key to end");
        final int read = System.in.read();
        log.info("Key pressed: " + read);
    }

}
