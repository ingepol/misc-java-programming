package io.javabrains.workshop;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Exercise7 {


    public static void main(final String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Print all values from intNumbersFlux that's greater than 5
        ReactiveSources.intNumbersFlux()
                .filter(num -> num > 5)
                .log()
                .subscribe(num -> log.info("Challenge 1: " + num.toString()));

        // Print 10 multiplied each value from intNumbersFlux that's greater than 5
        ReactiveSources.intNumbersFlux()
                .filter(num -> num > 5)
                .map(num -> num * 10)
                .subscribe(num -> log.info("Challenge 2 [multiplied]: " + num));

        // Print 10 multiplied value from intNumbersFlux 
        // for the first 3 numbers emitted that's greater than 5
        ReactiveSources.intNumbersFlux()
                .filter(num -> num > 5)
                .take(3)
                .map(num -> num * 10)
                .subscribe(num -> log.info("Challenge 3 [multiplied & take 3]:" + num));

        // Print each value from intNumbersFlux that's greater than 20. Print -1 if no elements are found
        ReactiveSources.intNumbersFlux()
                .filter(num -> num > 20)
                .defaultIfEmpty(-1)
                .subscribe(num -> log.info("Challenge 4 [> 20, -1 if no elements]:" + num));

        // Switch ints from intNumbersFlux to the right user from userFlux
        ReactiveSources.intNumbersFlux()
                .flatMap(id -> ReactiveSources.userFlux()
                        .filter(u -> u.getId() == id).take(1))
                .subscribe(u -> log.info("Challenge 5: " + u.toString()));

        // Print only distinct numbers from intNumbersFluxWithRepeat
        ReactiveSources.intNumbersFluxWithRepeat()
                .distinct()
                .subscribe(num -> log.info("Challenge 6 [Distinct]: " + num.toString()));

        // Print from intNumbersFluxWithRepeat excluding immediately repeating numbers
        ReactiveSources.intNumbersFluxWithRepeat()
                .distinctUntilChanged()
                .subscribe(num -> log.info("Challenge 7 [Excluding]: " + num.toString()));


        log.info("Press a key to end");
        final int read = System.in.read();
        log.info("Key pressed: " + read);
    }

}
