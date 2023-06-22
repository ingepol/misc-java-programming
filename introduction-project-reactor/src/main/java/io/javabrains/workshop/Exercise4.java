package io.javabrains.reactiveworkshop;

import io.javabrains.workshop.ReactiveSources;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Exercise4 {

    public static void main(final String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        ReactiveSources.intNumberMono().subscribe(value -> log.info(value.toString()));

        // Get the value from the Mono into an integer variable
        final Integer value = ReactiveSources.intNumberMono().block();
        log.info("Number: " + value);

        log.info("Press a key to end");
        final int read = System.in.read();
        log.info("Key pressed: " + read);
    }

}
