package io.javabrains.workshop;

import com.project.reactor.common.subscirber.BackpressureSubscriber;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Exercise5 {

    public static void main(final String[] args) throws IOException {

        // Use ReactiveSources.intNumberFlux() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumbersFlux().subscribe(
                num -> log.info(num.toString()),
                err -> log.error(err.getMessage()),
                () -> log.info("Complete!")
        );

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumbersFlux().subscribe(new BackpressureSubscriber<>());

        log.info("Press a key to end");
        final int read = System.in.read();
        log.info("Key pressed: " + read);
    }

}