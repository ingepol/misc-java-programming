package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class MapOperation {

  public static void main(String[] args) {
    Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .map(e -> e * 25)
        .forEach(e -> log.info(String.valueOf(e)));

    List<Book> books = Book.getBooks();

    // Stream Pipeline
    books.stream()
        .filter(book -> book.getGenre().equals(Genre.HORROR))
        .filter(book -> book.getRating() > 3)
        .map(Book::getName)
        .forEach(log::info);
  }
}
