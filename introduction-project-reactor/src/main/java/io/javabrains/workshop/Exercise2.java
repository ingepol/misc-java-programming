package io.javabrains.workshop;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Exercise2 {

    public static void main(final String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux() and ReactiveSources.userFlux()

        // Print all numbers in the ReactiveSources.intNumbersFlux stream
        ReactiveSources.intNumbersFlux()
                .subscribe(num -> log.info(num.toString()));

        // Print all users in the ReactiveSources.userFlux stream
        ReactiveSources.userFlux()
                .subscribe(user -> log.info(user.toString()));

        log.info("Press a key to end");
        final int read = System.in.read();
        log.info("Key pressed: " + read);
    }

}
