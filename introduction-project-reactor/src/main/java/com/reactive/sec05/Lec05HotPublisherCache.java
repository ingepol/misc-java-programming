package com.reactive.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec05HotPublisherCache {

  public static void main(final String[] args) {

    final Flux<String> movieStream = Flux
        .fromStream(Lec05HotPublisherCache::getMovies)
        .delayElements(Duration.ofSeconds(1))
        .publish()
        .autoConnect(0); // min subscriber to start emit content

    sleepSeconds(3);

    movieStream.subscribe(subscriber("Sam"));
    sleepSeconds(7);

    log.info("Mike is about to join");
    movieStream.subscribe(subscriber("Mike"));

    sleepSeconds(30);
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
