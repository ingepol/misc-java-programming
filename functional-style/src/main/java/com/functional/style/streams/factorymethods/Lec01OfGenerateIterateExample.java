package com.functional.style.streams.factorymethods;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;
import java.util.stream.Stream;

@Slf4j
public class Lec01OfGenerateIterateExample {

    public static void main(final String[] args) {
        final Stream<String> stringStream = Stream.of("Adam", "Dan", "Julie");
        stringStream.forEach(log::info);

        Stream.iterate(1, x -> x * 2)
                .limit(10) // break infinite stream
                .forEach(integer -> log.info(integer.toString()));// infinite stream

        final Supplier<Integer> integerSupplier = () -> Faker.instance().random().nextInt(10);
        Stream.generate(integerSupplier)
                .limit(5)
                .forEach(integer -> log.info(integer.toString()));

    }
}
