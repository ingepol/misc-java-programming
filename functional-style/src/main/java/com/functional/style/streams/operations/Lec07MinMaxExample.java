package com.functional.style.streams.operations;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Lec07MinMaxExample {

    public static int findMaxValue(final List<Integer> integerList) {
        return integerList.stream()
                .reduce(0, (a, b) -> a > b ? a : b);
    }

    public static Optional<Integer> findMaxValueOptional(final List<Integer> integerList) {
        return integerList.stream()
                .reduce((a, b) -> a > b ? a : b);
    }

    // Min value never has Entity Value
    public static Optional<Integer> findMinValue(final List<Integer> integerList) {
        return integerList.stream()
                .reduce((a, b) -> a < b ? a : b);
    }

    public static void main(final String[] args) {
        final List<Integer> integerList = Arrays.asList(6, 7, 8, 9, 10);
        //final List<Integer> integerList = new ArrayList<>();
        final int maxValue = findMaxValue(integerList);
        final Optional<Integer> maxValueOptional = findMaxValueOptional(integerList);
        log.info("max value is: {}", maxValue);
        log.info("Optional Max value is: {}", maxValueOptional);
        if (maxValueOptional.isPresent()) {
            log.info("max value optional is: {}", maxValue);
        } else {
            log.info("Input list is empty");
        }

        final Optional<Integer> minValueOptional = findMaxValueOptional(integerList);
        log.info("Optional Min value is: {}", minValueOptional);
        if (minValueOptional.isPresent()) {
            log.info("min value optional is: {}", maxValue);
        } else {
            log.info("Input list is empty");
        }

    }
}
