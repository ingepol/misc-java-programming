package com.basicstrong.functionalprogramming.section10;

import com.basicstrong.functionalprogramming.section9.Book;
import com.basicstrong.functionalprogramming.section9.Genre;

import java.util.Spliterator;
import java.util.function.Consumer;

public class BookSpliterator implements Spliterator<Book> {

  private String name;
  private String author;
  private Genre genre;
  private double rating;
  private Spliterator<String> baseSpliterator;

  public BookSpliterator(Spliterator<String> baseSpliterator) {
    this.baseSpliterator = baseSpliterator;
  }

  @Override
  public boolean tryAdvance(Consumer<? super Book> consumer) {
    if (
        this.baseSpliterator.tryAdvance(name -> this.name = name) &&
            this.baseSpliterator.tryAdvance(author -> this.author = author) &&
            this.baseSpliterator.tryAdvance(genre -> this.genre = Genre.getEnumByString(genre)) &&
            this.baseSpliterator.tryAdvance(rating -> this.rating = Double.parseDouble(rating))) {

      consumer.accept(Book.builder()
          .name(this.name)
          .genre(this.genre)
          .author(this.author)
          .rating(this.rating)
          .build());
      return true;
    }
    return false;
  }

  @Override
  public Spliterator<Book> trySplit() {
    return null;
  }

  @Override
  public long estimateSize() {
    return baseSpliterator.estimateSize() / 4;
  }

  @Override
  public int characteristics() {
    return baseSpliterator.characteristics();
  }
}
