package com.reactive.sec03;

import com.reactive.sec03.assignment.FileService;
import com.reactive.sec03.assignment.FileServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec09AssignmentDemo {

  public static void main(final String[] args) {
    final Path path = Paths.get("src", "main", "resources",
        "/reactor/assignment/sec03/file01.txt");
    final FileService service = new FileServiceImpl();
    service.write(path, 10).subscribe(subscriber());
    service.read(path).subscribe(subscriber());

  }
}
