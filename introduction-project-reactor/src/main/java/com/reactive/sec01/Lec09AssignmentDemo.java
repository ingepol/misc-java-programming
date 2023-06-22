package com.reactive.sec01;

import com.reactive.sec01.assignment.FileService;
import com.reactive.sec01.assignment.FileServiceImpl;

import java.io.IOException;

import static com.reactive.utils.ReactiveUtility.*;

public class Lec09AssignmentDemo {

  public static void main(final String[] args) throws IOException {
    final FileService service = new FileServiceImpl("sec01");
    final String fileName = "file01.txt";
    service.write(fileName, "This is file one").subscribe(
        onNext(),
        onError(),
        onComplete()
    );
    readFile(service, fileName);
    service.delete(fileName).subscribe(
        onNext(),
        onError(),
        onComplete()
    );
    readFile(service, fileName);
  }

  private static void readFile(final FileService service, final String fileName) {
    service.read(fileName).subscribe(
        onNext(),
        onError(),
        onComplete()
    );
  }
}
