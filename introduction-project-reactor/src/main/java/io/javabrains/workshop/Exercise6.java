package io.javabrains.workshop;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Duration;

@Slf4j
public class Exercise6 {


    public static void main(final String[] args) throws IOException {

        // Use ReactiveSources.unresponsiveFlux() and ReactiveSources.unresponsiveMono()

        // Get the value from the Mono into a String variable but give up after 5 seconds
        final String foo = ReactiveSources.unresponsiveMono().block(Duration.ofSeconds(5));
        log.info("Challenge 1:" + foo);

        // Get the value from unresponsiveFlux into a String list but give up after 5 seconds
        // Come back and do this when you've learnt about operators!
        // TODO: Write code here


        log.info("Press a key to end");
        final int read = System.in.read();
        log.info("Key pressed: " + read);
    }

}
