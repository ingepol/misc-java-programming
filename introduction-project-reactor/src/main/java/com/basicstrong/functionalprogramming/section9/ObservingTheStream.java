package com.basicstrong.functionalprogramming.section9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class ObservingTheStream {

  public static void main(String[] args) {
    List<Book> books = new ArrayList<>();
    books.add(Book.builder().author("Pol").genre(Genre.HORROR).rating(3.5).build());

    // Stream Pipeline
    List<Book> popularHorrorBooks = books.stream() //source
        .filter(book -> book.getGenre().equals(Genre.HORROR)) // Intermediate Op
        .filter(book -> book.getRating() > 3) // Intermediate Op
        .collect(Collectors.toList()); //Terminal Operation
    //1.
    Stream<Book> stream = books.stream();
    //2.
    Stream<Book> horrorBook = stream.filter(book -> book.getGenre().equals(Genre.HORROR));
    //3.
    Stream<Book> pHorrorBook = horrorBook.filter(book -> book.getRating() > 3);
    //4.
    List<Book> collect = pHorrorBook.collect(Collectors.toList());


  }
}
