package com.functional.style.streams.operations;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Lec08LimitSkipExample {

    public static Optional<Integer> limit(final List<Integer> integerList) {
        return integerList.stream()
                .limit(3)
                .reduce(Integer::sum);
    }

    public static Optional<Integer> skip(final List<Integer> integerList) {
        return integerList.stream()
                .skip(3) //9, 10
                .reduce(Integer::sum);
    }

    public static void main(final String[] args) {
        final List<Integer> integerList = Arrays.asList(6, 7, 8, 9, 10);
        final Optional<Integer> limit = limit(integerList);
        limit.ifPresent(integer -> log.info("The Limit result {}", integer));

        final Optional<Integer> skip = skip(integerList);
        skip.ifPresent(integer -> log.info("The Skip result {}", integer));

    }
}
