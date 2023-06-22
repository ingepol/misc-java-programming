package com.reactive.sec03.assignment;

import reactor.core.publisher.Flux;

import java.nio.file.Path;

public interface FileService {

  Flux<String> read(Path path);

  Flux<String> write(Path path, int numOfLines);
}
