package com.reactive.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec03HotPublisherRefCount {

  public static void main(final String[] args) {
    // Share = publish().refCount(1)
    final Flux<String> movieStream = Flux
        .fromStream(Lec03HotPublisherRefCount::getMovies)
        .delayElements(Duration.ofSeconds(1))
        .publish()
        .refCount(1); // min subscriber to start emit content

    movieStream.subscribe(subscriber("Sam"));
    sleepSeconds(10);

    // if when mike subscribe the previous emit was completed,
    // the publisher start emit again from begin
    movieStream.subscribe(subscriber("Mike"));

    sleepSeconds(60);
  }

  //movie-theatre
  private static Stream<String> getMovies() {
    log.info("Got the movies streaming req");
    return Stream.of(
        "Scene 1",
        "Scene 2",
        "Scene 2",
        "Scene 4",
        "Scene 5",
        "Scene 6",
        "Scene 7"
    );
  }
}
