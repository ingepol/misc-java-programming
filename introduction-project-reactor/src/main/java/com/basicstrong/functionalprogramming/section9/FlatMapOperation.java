package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@SuppressWarnings({"useless", "unused"})
public class FlatMapOperation {

  public static void main(String[] args) {
    Stream<String> a = Stream.of("Hello", "there! ");
    Stream<String> b = Stream.of("Learning", "Java? ");

    Stream<Stream<String>> of = Stream.of(a, b);

    Stream<String> stringStream = Stream.of(a, b).flatMap(e -> e);

    String resource = FlatMapOperation.class.getResource("/texts/LoremIpsum.txt").getPath();

    Path p = Paths.get(resource);

    try (Stream<String> noteBook = Files.lines(p)) {

      /**
       // Split Lines by space and creating new list with an array of strings by line.
       List<String[]> collect = noteBook.map(line -> line.split(" "))
       .collect(Collectors.toList());
       collect.forEach(val -> log.info(Arrays.toString(val)));
       */
      // Split Lines by space and creating a new stream from an array of string, then create a 
      // collect list of String. 
      List<String> collect1 = noteBook
          .flatMap(line -> Arrays.stream(line.split(" ")))
          .collect(Collectors.toList());
      collect1.forEach(log::info);

    } catch (IOException e) {
      log.error(e.getMessage());
    }


  }

  private InputStream getFileFromResourceAsStream(String fileName) {

    // The class loader that loaded the class
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);

    // the stream holding the file content
    if (inputStream == null) {
      throw new IllegalArgumentException("file not found! " + fileName);
    } else {
      return inputStream;
    }

  }
}
