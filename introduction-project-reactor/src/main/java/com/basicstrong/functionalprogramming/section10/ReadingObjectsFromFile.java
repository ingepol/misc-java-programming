package com.basicstrong.functionalprogramming.section10;

import com.basicstrong.functionalprogramming.section9.Book;
import com.basicstrong.functionalprogramming.section9.FlatMapOperation;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
public class ReadingObjectsFromFile {

  public static void main(String[] args) {
    String resource = Objects.requireNonNull(
        FlatMapOperation.class.getResource("/texts/Books.txt")).getPath();

    Path p = Paths.get(resource);

    try (Stream<String> lines = Files.lines(p)) {
      Spliterator<String> baseSpliterator = lines.spliterator();
      Spliterator<Book> spliterator = new BookSpliterator(baseSpliterator);

      Stream<Book> stream = StreamSupport.stream(spliterator, false);
      stream.forEach(book -> log.info(book.toString()));

      log.info(String.valueOf(spliterator.estimateSize()));
      log.info(String.valueOf(spliterator.characteristics()));

    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }
}
