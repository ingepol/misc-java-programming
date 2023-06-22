package io.javabrains.workshop;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
public class Exercise9 {


    public static void main(final String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()
        final Flux<Integer> integerFlux = ReactiveSources.intNumbersFlux();
        // Print size of intNumbersFlux after the last item returns
        integerFlux.count().subscribe(size -> log.info("Challenge 1 [size]:" + size));

        // Collect all items of intNumbersFlux into a single list and print it
        integerFlux.collectList().subscribe(list -> log.info("Challenge 2 [list]" + list));

        // Transform to a sequence of sums of adjacent two numbers
        integerFlux.buffer(2)
                .map(list -> list.get(0) + list.get(1))
                .subscribe(sum -> log.info("Challenge 3 [sum]:" + sum));

        System.out.println("Press a key to end");
        System.in.read();
    }

}
