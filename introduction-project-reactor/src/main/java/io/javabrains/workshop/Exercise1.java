package io.javabrains.workshop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Exercise1 {

  public static void main(final String[] args) {

    // Use StreamSources.intNumbersStream() and StreamSources.userStream()

    // Print all numbers in the intNumbersStream stream
    log.info("\n\n=== Challenge 1 ===");
    StreamSources.intNumbersStream().forEach(num -> log.info(num.toString()));

    // Print numbers from intNumbersStream that are less than 5
    log.info("\n\n=== Challenge 2 ===");
    StreamSources.intNumbersStream().filter(num -> num < 5)
        .forEach(num -> log.info(num.toString()));

    // Print the second and third numbers in intNumbersStream that's greater than 5
    log.info("\n\n=== Challenge 3 ===");
    StreamSources.intNumbersStream().filter(num -> num > 5)
        .skip(1) // ignore first one
        .limit(2) // get the next 2 numbers
        .forEach(num -> log.info(num.toString()));

    //  Print the first number in intNumbersStream that's greater than 5.
    //  If nothing is found, print -1
    log.info("\n\n=== Challenge 4 ===");
    final Integer value = StreamSources.intNumbersStream().filter(num -> num > 5)
        .findFirst()
        .orElse(-1);
    log.info(value.toString());

    // Print first names of all users in userStream
    log.info("\n\n=== Challenge 5 ===");
    StreamSources
        .userStream()
        .map(User::getFirstName)
        .forEach(log::info);

    // Print first names in userStream for users that have IDs from number stream
    log.info("\n\n=== Challenge 6.1 ===");

    StreamSources.userStream()
        .filter(u -> StreamSources.intNumbersStream().anyMatch(i -> i == u.getId()))
        .map(User::toString)
        .forEach(log::info);

    log.info("\n\n=== Challenge 6.2 ===");
    StreamSources
        .intNumbersStream()
        .flatMap(id -> StreamSources
            .userStream()
            .filter(user -> user.getId() == id)
        ).map(User::getFirstName)
        .forEach(log::info);

  }

}
