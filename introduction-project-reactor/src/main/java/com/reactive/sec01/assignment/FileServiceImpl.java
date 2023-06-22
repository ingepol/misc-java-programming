package com.reactive.sec01.assignment;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileServiceImpl implements FileService {

  private final Path path;

  public FileServiceImpl(String section) {
    this.path = Paths.get("src", "main", "resources",
        "/reactor/assignment", section);
  }

  @Override
  public Mono<String> read(String filename) {
    return Mono.fromSupplier(() -> readFile(filename));
  }

  @Override
  public Mono<Void> write(String filename, String content) {
    return Mono.fromRunnable(() -> writeFile(filename, content));
  }

  @Override
  public Mono<Void> delete(String filename) {
    return Mono.fromRunnable(() -> deleteFile(filename));
  }

  private void writeFile(String filename, String content) {
    try {
      Files.writeString(path.resolve(filename), content);
    } catch (IOException e) {
      throw new FileServiceException("Writing error", e);
    }
  }

  private void deleteFile(String filename) {
    try {
      Files.delete(path.resolve(filename));
    } catch (IOException e) {
      throw new FileServiceException("Deleting error", e);
    }
  }

  private String readFile(String filename) {
    try {
      return Files.readString(path.resolve(filename));
    } catch (IOException e) {
      throw new FileServiceException("Reading error", e);
    }
  }
}
