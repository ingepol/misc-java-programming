package com.learnreactiveprograming.service;

import com.learnreactiveprograming.exception.ReactorException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.function.UnaryOperator;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class FluxAndMonoGeneratorService {

  public static final List<String> listNames = List.of("alex", "ben", "chloe");

  public static final String DEFAULT = "default";

  public static final String EXCEPTION_OCCURRED = "Exception Occurred";

  public Flux<String> namesFlux() {
    return Flux.fromIterable(listNames).log(); // db or a remote service call
  }

  public Mono<String> nameMono() {
    return Mono.just(faker().name().fullName()).log();
  }

  public Mono<String> nameMonoMapAndFilter(final int stringLength) {
    return Mono.just("alex")
        .map(String::toUpperCase)
        .filter(s -> s.length() > stringLength)
        .map(s -> s.length() + "-" + s)
        .defaultIfEmpty(DEFAULT)
        .log();
  }

  public Mono<String> nameMonoMapAndFilterWithSwitchIfEmpty(final int stringLength) {
    final UnaryOperator<Mono<String>> transform = name -> name.map(String::toUpperCase)
        .filter(s -> s.length() > stringLength)
        .map(s -> s.length() + "-" + s);
    return Mono.just("alex")
        .transform(transform)
        .switchIfEmpty(Mono.just(DEFAULT).transform(transform))
        .log();
  }

  public Mono<List<String>> nameMonoFlatMap(final int stringLength) {
    return Mono.just("alex")
        .map(String::toUpperCase)
        .filter(s -> s.length() > stringLength)
        .flatMap(this::splitStringMono) //Mono<List of A, L, E, X)
        .log();
  }

  public Flux<String> nameMonoFlatMapMany(final int stringLength) {
    return Mono.just("alex")
        .map(String::toUpperCase)
        .filter(s -> s.length() > stringLength)
        .flatMapMany(this::splitString) //Mono<List of A, L, E, X)
        .log();
  }

  public Mono<String> exploreMonoZip() {
    final Mono<String> monoA = Mono.just("A");
    final Mono<String> monoB = Mono.just("B");

    return monoA.zipWith(monoB)
        .map(t2 -> t2.getT1() + t2.getT2())
        .log();
  }

  private Mono<List<String>> splitStringMono(final String s) {
    final var charArray = s.split("");
    return Mono.just(List.of(charArray));
  }

  public Flux<String> namesFluxMap() {
    return Flux.fromIterable(listNames)
        .map(String::toUpperCase).log();
  }

  public Flux<String> namesFluxImmutability() {
    final Flux<String> nameFlux = Flux.fromIterable(listNames);
    nameFlux.map(String::toUpperCase); // This map is ignores, this is the immutable nature of reactor's streams.
    return nameFlux;
  }

  public Flux<String> namesFluxMapAndFilter(final int stringLength) {
    return Flux.fromIterable(listNames)
        .map(String::toUpperCase)
        .filter(s -> s.length() > stringLength)
        .map(s -> s.length() + "-" + s)
        .log();
  }

  public Flux<String> namesFluxFlatMap(final int stringLength) {
    return Flux.fromIterable(listNames)
        .doOnNext(name -> log.info("Name before Upper: {}", name))
        .map(String::toUpperCase)
        .doOnNext(name -> log.info("Name After Upper: {}", name))
        .filter(s -> s.length() > stringLength)
        .doOnNext(name -> log.info("Name pass the filter: {}", name))
        // ALEX, CHLOE -> A,L,E,X,C,H,L,O,E
        .flatMap(this::splitString)
        .doOnNext(name -> log.info("Name is: {}", name))
        .doOnSubscribe(s -> log.info("Subscription is: {}", s))
        .doOnComplete(() -> log.info("Inside the complete callback"))
        .doFinally(signalType -> log.info("inside doFinally: {}", signalType));
  }

  public Flux<String> namesFluxFlatMapAsync(final int stringLength) {
    return Flux.fromIterable(listNames)
        .map(String::toUpperCase)
        .filter(s -> s.length() > stringLength)
        // ALEX, CHLOE -> A,L,E,X,C,H,L,O,E
        .flatMap(this::splitStringWithDelay)
        .log();
  }

  public Flux<String> namesFluxConcatMap(final int stringLength) {
    return Flux.fromIterable(listNames)
        .map(String::toUpperCase)
        .filter(s -> s.length() > stringLength)
        // ALEX, CHLOE -> A,L,E,X,C,H,L,O,E
        .concatMap(this::splitStringWithDelay)
        .log();
  }

  public Flux<String> namesFluxTransform(final int stringLength) {
    final UnaryOperator<Flux<String>> filterMap = name -> name.map(String::toUpperCase)
        .filter(s -> s.length() > stringLength);
    return Flux.fromIterable(listNames)
        .transform(filterMap)
        .flatMap(this::splitString)
        .defaultIfEmpty("default")
        .log();
  }

  public Flux<String> namesFluxTransformSwitchIfEmpty(final int stringLength) {
    final UnaryOperator<Flux<String>> filterMap = name -> name.map(String::toUpperCase)
        .filter(s -> s.length() > stringLength)
        .flatMap(this::splitString);

    final Flux<String> defaultFlux = Flux.just("default")
        .transform(filterMap); //"D", "E", "F", "A", "U", "L", "T"

    return Flux.fromIterable(listNames)
        .transform(filterMap)
        .switchIfEmpty(defaultFlux)
        .log();
  }

  public Flux<String> exploreConcat() {
    final Flux<String> justA = Flux.just("A", "B", "C");
    final Flux<String> justB = Flux.just("D", "E", "F");
    return Flux.concat(justA, justB);
  }

  public Flux<String> exploreConcatWith() {
    final Flux<String> justA = Flux.just("A", "B", "C");
    final Flux<String> justB = Flux.just("D", "E", "F");
    return justA.concatWith(justB);
  }

  public Flux<String> exploreConcatWithMono() {
    final Mono<String> aMono = Mono.just("A");
    final Mono<String> bMono = Mono.just("D");
    return aMono.concatWith(bMono);
  }

  public Flux<String> exploreMerge() {
    final Flux<String> justA = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));
    final Flux<String> justB = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(125));
    return Flux.merge(justA, justB).log();
  }

  public Flux<String> exploreMergeWith() {
    final Flux<String> justA = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));
    final Flux<String> justB = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(125));
    return justA.mergeWith(justB);
  }

  public Flux<String> exploreMergeWithMono() {
    final Mono<String> aMono = Mono.just("A");
    final Mono<String> bMono = Mono.just("D");
    return aMono.mergeWith(bMono);
  }

  public Flux<String> exploreMergeSequential() {
    final Flux<String> justA = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));
    final Flux<String> justB = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(125));
    return Flux.mergeSequential(justA, justB);
  }

  public Flux<String> exploreZip() {
    final Flux<String> justA = Flux.just("A", "B", "C");
    final Flux<String> justB = Flux.just("D", "E", "F");
    return Flux.zip(justA, justB, (first, second) -> first + second); //AD, BE, CF
  }

  public Flux<String> exploreZipWith4Fluxes() {
    final Flux<String> justA = Flux.just("A", "B", "C");
    final Flux<String> justB = Flux.just("D", "E", "F");
    final Flux<String> flux1 = Flux.just("1", "2", "3");
    final Flux<String> flux2 = Flux.just("4", "5", "6");
    return Flux.zip(justA, justB, flux1, flux2) //The map function must be used instead of combinator, when we got more than 2 flux.
        .map(t4 -> t4.getT1() + t4.getT2() + t4.getT3() + t4.getT4()).log();
  }

  public Flux<String> exploreZipWith() {
    final Flux<String> justA = Flux.just("A", "B", "C");
    final Flux<String> justB = Flux.just("D", "E", "F");
    return justA.zipWith(justB, (first, second) -> first + second); //AD, BE, CF
  }

  public Flux<String> exceptionFlux() {
    return Flux.just("A", "B", "C")
        .concatWith(Flux.error(new RuntimeException(EXCEPTION_OCCURRED)))
        .concatWith(Flux.just("D"))
        .log();
  }

  public Flux<String> exploreOnErrorReturn() {
    return Flux.just("A", "B", "C")
        .concatWith(Flux.error(new IllegalStateException(EXCEPTION_OCCURRED)))
        .onErrorReturn("D") // Catch and provide a recoverable single default value.
        .log();
  }

  public Flux<String> exploreOnErrorResume(final Exception e) {
    final Flux<String> recoveryFlux = Flux.just("D", "E", "F");
    return Flux.just("A", "B", "C")
        .concatWith(Flux.error(e))
        // Catch and provide other dynamic reactive stream as fallback value.
        .onErrorResume(ex -> {
          log.error("Exception is: ", ex);
          if (ex instanceof IllegalStateException) { // Conditional recovery.
            return recoveryFlux; // reactive stream fallback.
          } else {
            return Flux.error(ex);
          }
        })
        .log();
  }

  public Flux<String> exploreOnErrorContinue() {
    final List<String> names = List.of("A", "B", "C");
    return Flux.fromIterable(names)
        .map(name -> {
          if (name.equals("B")) {
            throw new IllegalStateException("Exception Occurred");
          }
          return name;
        })
        .concatWith(Flux.just("D"))
        // Catch and allows the reactive stream to continue emitting elements.
        .onErrorContinue((ex, name) -> {
          log.error("Exception is: ", ex);
          log.info("name is: {}", name);
        })
        .log();
  }

  public Mono<String> exploreMonoOnErrorContinue(final String input) {
    return Mono.just(input)
        .map(value -> {
          if ("abc".equals(value)) {
            throw new IllegalStateException(EXCEPTION_OCCURRED);
          }
          return value;
        }).onErrorContinue((ex, value) -> {
          log.error("Exception is: ", ex);
          log.info("name is: {}", value);
        });
  }

  public Flux<String> exploreOnErrorMap() {
    final List<String> names = List.of("A", "B", "C");
    return Flux.fromIterable(names)
        .map(name -> {
          if (name.equals("B")) {
            throw new IllegalStateException("Exception Occurred");
          }
          return name;
        })
        .concatWith(Flux.just("D"))
        // Catch and transform to some Custom Exception Type.
        .onErrorMap(ex -> {
          log.error("Exception is: ", ex);
          return new ReactorException(ex, ex.getMessage());
        })
        .log();
  }

  public Flux<String> exploreDoOnError() {
    return Flux.just("A", "B", "C")
        .concatWith(Flux.error(new IllegalStateException(EXCEPTION_OCCURRED)))
        // Catch and propagate it down stream.
        .doOnError(ex -> {
          log.error("Exception is ", ex);
        })
        .log();
  }

  public Mono<String> exploreMonoOnErrorReturn() {
    return Mono.just("A")
        .map(value -> {
          throw new IllegalStateException(EXCEPTION_OCCURRED);
        })
        .onErrorReturn("abc")
        .cast(String.class)
        .log();
  }

  public Mono<String> exploreMonoOnErrorMap() {
    return Mono.just("B")
        .map(value -> {
          throw new IllegalStateException(EXCEPTION_OCCURRED);
        })
        .onErrorMap(ex -> {
          log.error("Exception is ", ex);
          return new ReactorException(ex, ex.getMessage());
        })
        .cast(String.class)
        .log();
  }

  private Flux<String> splitString(final String value) {
    final String[] split = value.split("");
    return Flux.fromArray(split);
  }

  private Flux<String> splitStringWithDelay(final String value) {
    final String[] split = value.split("");
    final int delay = 1000;
    return Flux.fromArray(split)
        .delayElements(Duration.ofMillis(delay));
  }

  public Mono<String> getString(final String id) {
    return Mono.just(id)
        .filter(s -> !s.isBlank())
        .map(String::toUpperCase)
        .switchIfEmpty(Mono.error(new IllegalStateException("Empty")));
  }

  public static void main(final String[] args) {
    final FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();
    service.namesFlux().subscribe(subscriber("flux")); // multiple elements
    service.nameMono().subscribe(subscriber("mono")); // one element
  }

}
