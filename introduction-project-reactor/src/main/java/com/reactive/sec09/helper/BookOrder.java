package com.reactive.sec09.helper;

import com.github.javafaker.Book;
import lombok.Getter;
import lombok.ToString;

import static com.reactive.utils.FakerUtility.faker;

@Getter
@ToString
public class BookOrder {

  private final String title;

  private final String author;

  private final String category;

  private final double price;

  public BookOrder() {
    final Book book = faker().book();
    this.title = book.title();
    this.author = book.author();
    this.category = book.genre();
    this.price = Double.parseDouble(faker().commerce().price().replace(",", "."));
  }

}
