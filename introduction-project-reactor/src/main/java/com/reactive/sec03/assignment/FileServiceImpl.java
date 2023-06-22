package com.reactive.sec03.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

public class FileServiceImpl implements FileService {

  private Callable<BufferedReader> openReader(Path path) {
    return () -> Files.newBufferedReader(path);
  }

  private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
    return (br, sink) -> {
      try {
        String line = br.readLine();
        if (isNull(line)) {
          sink.complete();
        } else {
          sink.next(line);
        }
      } catch (IOException e) {
        sink.error(e);
      }
      return br;
    };
  }

  private Consumer<FluxSink<String>> writeLines(
      Path path, int numberOfLines) {
    return sync -> {
      try (BufferedWriter bw = Files.newBufferedWriter(path)) {
        for (int i = 0; i < numberOfLines; i++) {
          bw.append(String.format("Line %d%n", i + 1));
        }
        sync.complete();
      } catch (IOException e) {
        sync.error(e);
      }
    };
  }

  private Consumer<BufferedReader> closeReader() {
    return br -> {
      try {
        br.close();
      } catch (IOException e) {
        Thread.currentThread().interrupt();
      }
    };
  }

  @Override
  public Flux<String> read(Path path) {
    return Flux.generate(openReader(path), read(), closeReader());
  }

  @Override
  public Flux<String> write(Path path, int numOfLines) {
    return Flux.create(writeLines(path, numOfLines));
  }
}
