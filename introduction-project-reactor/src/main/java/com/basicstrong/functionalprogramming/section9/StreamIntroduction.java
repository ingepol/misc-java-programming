package com.basicstrong.functionalprogramming.section9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class StreamIntroduction {

  public static void main(String[] args) {
    List<Book> books = new ArrayList<>();
    books.add(Book.builder().author("Pol").genre(Genre.HORROR).rating(3.5).build());
    List<Book> popularHorrorBooks = new ArrayList<>();
    for (Book book : books) {
      if (book.getGenre().equals(Genre.HORROR) && book.getRating() > 3) {
        popularHorrorBooks.add(book);
      }
    }

    //after java 8

    List<Book> collect = books.parallelStream()
        .filter(book -> book.getGenre().equals(Genre.HORROR))
        .filter(book -> book.getRating() > 3)
        .collect(Collectors.toList());
  }
}
