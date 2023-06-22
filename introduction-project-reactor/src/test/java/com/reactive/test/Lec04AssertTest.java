package com.reactive.test;

import com.reactive.sec09.helper.BookOrder;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class Lec04AssertTest {

  @Test
  void test1() {
    final Mono<BookOrder> mono = Mono.fromSupplier(BookOrder::new);
    StepVerifier.create(mono)
        .assertNext(b -> assertNotNull(b.getAuthor()))
        .verifyComplete();
  }

  @Test
  void test2() {
    final Mono<BookOrder> mono = Mono.fromSupplier(BookOrder::new)
        .delayElement(Duration.ofSeconds(2));
    StepVerifier.create(mono)
        .assertNext(b -> assertNotNull(b.getAuthor()))
        .expectComplete()
        .verify(Duration.ofSeconds(3));
  }
}
