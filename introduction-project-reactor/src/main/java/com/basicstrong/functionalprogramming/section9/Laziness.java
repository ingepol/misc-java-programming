package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class Laziness {

  public static void main(String[] args) {
    List<Book> books = Book.getBooks();

    // Stream Pipeline
    Stream<Book> bookStream = books.stream()
        .filter(book -> book.getGenre().equals(Genre.HORROR))
        .peek(book -> log.info("Filtering " + book))
        .filter(book -> book.getRating() > 3);

    log.info("Filtering done!");

    collect(bookStream);

  }

  private static void collect(Stream<Book> bookStream) {
    List<Book> popularBooks = bookStream.collect(Collectors.toList());
    log.info("Collection done!");
    popularBooks.forEach(book -> log.info(book.toString()));
  }

}
