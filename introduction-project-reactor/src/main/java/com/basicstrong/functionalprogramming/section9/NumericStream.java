package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Slf4j
@SuppressWarnings("unused")
public class NumericStream {

  public static void main(String[] args) {
    List<Book> books = Book.getBooks();
    double average = books.stream()
        .mapToDouble(Book::getRating)
        .average().orElse(0);
    log.info(String.valueOf(average));

    // map: Objects
    // mapToDouble: double primitive

    IntStream intS = IntStream.of(1, 3, 5, 8);
    DoubleStream doubleS = DoubleStream.of(1, 3, 5, 8);
    LongStream longS = LongStream.of(1L, 2L, 3L, 5L, 8L);

    Stream<Integer> boxed = intS.boxed();
    Stream<Double> doubleStream = doubleS.mapToObj(val -> val); // equal doubleS.boxed()


  }
}
