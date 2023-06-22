package com.reactive.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec02HotPublisher {

  public static void main(final String[] args) {
    final Flux<String> movieStream = Flux
        .fromStream(Lec02HotPublisher::getMovies)
        .delayElements(Duration.ofSeconds(2))
        .share();
    movieStream.subscribe(subscriber("Sam"));
    sleepSeconds(5);

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
