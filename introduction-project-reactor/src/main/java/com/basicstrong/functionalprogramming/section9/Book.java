package com.basicstrong.functionalprogramming.section9;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class Book {

  private String name;
  private String author;
  private Genre genre;
  private double rating;

  public static List<Book> getBooks() {
    List<Book> books = new ArrayList<>();
    books.add(Book.builder().name("The Alchemist").author("Pol")
        .genre(Genre.ADVENTURE).rating(4.4)
        .build());
    books.add(Book.builder().name("The Notebook").author("Pol")
        .genre(Genre.ROMANCE).rating(4.1)
        .build());
    books.add(Book.builder().name("Horror Cocktail").author("Pol")
        .genre(Genre.HORROR).rating(2.7)
        .build());
    books.add(Book.builder().name("House of leaves").author("Pol")
        .genre(Genre.HORROR).rating(4.1)
        .build());

    return books;

  }

}
