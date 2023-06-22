package io.javabrains.workshop;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class Exercise3 {

    public static void main(final String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Get all numbers in the ReactiveSources.intNumbersFlux stream
        // into a List and print the list and its size
        final List<Integer> numbers = ReactiveSources
                .intNumbersFlux()
                .toStream().toList();

        log.info("List is: " + numbers);
        log.info("Size is: " + numbers.size());


        log.info("Press a key to end");
        final int read = System.in.read();
        log.info("Key pressed: " + read);
    }

}
