package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class SingleUse {

  public static void main(String[] args) {
    List<Book> books = Book.getBooks();

    Stream<Book> stream = books.stream();

    // Stream Pipeline
    List<Book> popularHorrorBooks = stream //source
        .filter(book -> book.getGenre().equals(Genre.HORROR)) // Intermediate Op
        .filter(book -> book.getRating() > 3) // Intermediate Op
        .collect(Collectors.toList()); //Terminal Operation

    popularHorrorBooks.forEach(book -> log.info(book.toString()));

    // java.lang.IllegalStateException: stream has already been operated upon or closed
    /*
      List<Book> popularRomanticBooks = stream //source
          .filter(book -> book.getGenre().equals(Genre.ROMANCE)) // Intermediate Op
          .filter(book -> book.getRating() > 3) // Intermediate Op
          .collect(Collectors.toList()); //Terminal Operation
  
      popularRomanticBooks.forEach(book -> log.info(book.toString()));
     */
  }
}
