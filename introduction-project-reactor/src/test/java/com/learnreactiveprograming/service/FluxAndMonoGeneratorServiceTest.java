package com.learnreactiveprograming.service;

import com.learnreactiveprograming.exception.ReactorException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

class FluxAndMonoGeneratorServiceTest {

  FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();

  @Test
  void shouldGetNamesFromFlux() {
    //given

    //when
    final Flux<String> namesFlux = this.service.namesFlux();

    //then
    StepVerifier.create(namesFlux)
        //.expectNext("alex", "ben", "chloe")
        //.expectNextCount(3)
        .expectNext("alex")
        .expectNextCount(2)
        .verifyComplete();
  }

  @Test
  void shouldGetAlexNameFromMono() {
    //given

    //when
    final Mono<String> nameMono = this.service.nameMono();

    //then
    StepVerifier.create(nameMono)
        .expectNextCount(1)
        .verifyComplete();

  }

  @Test
  void shouldGetNameFromMono() {
    //given
    final int stringLength = 3;

    //when
    final Mono<String> nameFiltered = this.service.nameMonoMapAndFilter(stringLength);

    //then
    StepVerifier.create(nameFiltered)
        .expectNext("4-ALEX")
        .verifyComplete();

  }

  @Test
  void shouldGetDefaultNameFromMono() {
    //given
    final int stringLength = 4;

    //when
    final Mono<String> nameFiltered = this.service.nameMonoMapAndFilter(stringLength);

    //then
    StepVerifier.create(nameFiltered)
        .expectNext("default")
        .verifyComplete();
  }

  @Test
  void shouldGetDefaultCharacterNameFromMono() {
    //given
    final int stringLength = 4;

    //when
    final Mono<String> nameFiltered = this.service.nameMonoMapAndFilterWithSwitchIfEmpty(stringLength);

    //then
    StepVerifier.create(nameFiltered)
        .expectNext("7-DEFAULT")
        .verifyComplete();
  }

  @Test
  void shouldReturnAListOfCharacter() {
    //given
    final int stringLength = 3;
    //when
    final Mono<List<String>> listMono = this.service.nameMonoFlatMap(stringLength);
    //then
    StepVerifier.create(listMono)
        .expectNext(List.of("A", "L", "E", "X"))
        .verifyComplete();
  }

  @Test
  void shouldGetNamesInUppercaseFromFlux() {
    //given

    //when
    final Flux<String> fluxMapNames = this.service.namesFluxMap();

    //then
    StepVerifier.create(fluxMapNames)
        .expectNext("ALEX")
        .expectNextCount(2)
        .verifyComplete();

  }

  @Test
  void shouldGetNamesInLowercaseFromImmutableFlux() {
    //given

    //when
    final Flux<String> fluxNames = this.service.namesFluxImmutability();

    //then
    StepVerifier.create(fluxNames)
        .expectNext("alex")
        .expectNextCount(2)
        .verifyComplete();

  }

  @Test
  void shouldGetNamesWithLengthGreaterThanThreeFromImmutableFlux() {
    //given
    final int stringLength = 3;

    //when
    final Flux<String> namesFiltered = this.service.namesFluxMapAndFilter(stringLength);

    //then
    StepVerifier.create(namesFiltered)
        .expectNext("4-ALEX", "5-CHLOE")
        .verifyComplete();
  }

  @Test
  void shouldGetCharacterNamesFromFlux() {
    //given
    final int stringLength = 3;

    //when
    final Flux<String> fluxFlatMap = this.service.namesFluxFlatMap(stringLength);

    //then
    StepVerifier.create(fluxFlatMap)
        //.expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
        .expectNextCount(9)
        .verifyComplete();
  }

  @Test
  void shouldGetCharacterNamesFromFluxWithRandomDelay() {
    //given
    final int stringLength = 3;

    //when
    final Flux<String> fluxFlatMap = this.service.namesFluxFlatMapAsync(stringLength);

    //then
    StepVerifier.create(fluxFlatMap)
        // This was fail, because flatmap is used for async transformations and has a random delay to generate the next character.
        //.expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
        .expectNextCount(9)
        .verifyComplete();
  }

  @Test
  void shouldGetCharacterNamesFromFluxConcatMapWithRandomDelay() {
    //given
    final int stringLength = 3;

    //when
    final Flux<String> fluxFlatMap = this.service.namesFluxConcatMap(stringLength);

    //then
    StepVerifier.create(fluxFlatMap)
        // This was fail, because flatmap is used for async transformations and has a random delay to generate the next character.
        //.expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
        .expectNextCount(9)
        .verifyComplete();
  }

  @Test
  void shouldReturnAFluxOfCharacter() {
    //given
    final int stringLength = 3;
    //when
    final Flux<String> fluxCharacter
        = this.service.nameMonoFlatMapMany(stringLength);
    //then
    StepVerifier.create(fluxCharacter)
        .expectNext("A", "L", "E", "X")
        .verifyComplete();
  }

  @Test
  void shouldReturnAMonoStringWithMonoZip() {
    //given

    //when
    final Mono<String> monoZip
        = this.service.exploreMonoZip();
    //then
    StepVerifier.create(monoZip)
        .expectNext("AB")
        .verifyComplete();
  }

  @Test
  void shouldGetNamesInUppercaseFromFluxTransform() {
    //given
    final int stringLength = 3;
    //when
    final Flux<String> fluxMapNames = this.service.namesFluxTransform(stringLength);

    //then
    StepVerifier.create(fluxMapNames)
        .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
        .verifyComplete();

  }

  @Test
  void shouldGetDefaultNamesFromFluxTransform() {
    //given
    final int stringLength = 6;
    //when
    final Flux<String> fluxMapNames = this.service.namesFluxTransform(stringLength);

    //then
    StepVerifier.create(fluxMapNames)
        .expectNext("default")
        .verifyComplete();

  }

  @Test
  void shouldReturnUpperCase() {
    //given

    //when
    final Mono<String> upper = this.service.getString(StringUtils.EMPTY);

    //then
    StepVerifier.create(upper)
        .expectError(IllegalStateException.class)
        .verify();

  }

  @Test
  void shouldGetDefaultCharacterFromFluxTransformSwitchEmpty() {
    //given
    final int stringLength = 6;
    //when
    final Flux<String> fluxMapNames = this.service.namesFluxTransformSwitchIfEmpty(stringLength);

    //then
    StepVerifier.create(fluxMapNames)
        .expectNext("D", "E", "F", "A", "U", "L", "T")
        .verifyComplete();

  }

  @Test
  void shouldCombineTwoFluxUsingConcat() {
    //given

    //when
    final var concatFlux = this.service.exploreConcat();

    //then
    StepVerifier.create(concatFlux)
        .expectNext("A", "B", "C", "D", "E", "F")
        .verifyComplete();

  }

  @Test
  void shouldCombineTwoFluxUsingConcatWith() {
    //given

    //when
    final var concatFlux = this.service.exploreConcatWith();

    //then
    StepVerifier.create(concatFlux)
        .expectNext("A", "B", "C", "D", "E", "F")
        .verifyComplete();

  }

  @Test
  void shouldCombineTwoMonosUsingConcatWith() {
    //given

    //when
    final var concatFlux = this.service.exploreConcatWithMono();

    //then
    StepVerifier.create(concatFlux)
        .expectNext("A", "D")
        .verifyComplete();

  }

  @Test
  void shouldCombineTwoFluxUsingMerge() {
    //given

    //when
    final var mergeFlux = this.service.exploreMerge();

    //then
    StepVerifier.create(mergeFlux)
        .expectNext("A", "D", "B", "E", "C", "F")
        .verifyComplete();

  }

  @Test
  void shouldCombineTwoFluxUsingMergeWith() {
    //given

    //when
    final var concatFlux = this.service.exploreMergeWith();

    //then
    StepVerifier.create(concatFlux)
        .expectNext("A", "D", "B", "E", "C", "F")
        .verifyComplete();

  }

  @Test
  void shouldCombineTwoMonosUsingMergeWith() {
    //given

    //when
    final var concatFlux = this.service.exploreMergeWithMono();

    //then
    StepVerifier.create(concatFlux)
        .expectNext("A", "D")
        .verifyComplete();

  }

  @Test
  void shouldCombineTwoFluxUsingMergeSequential() {
    //given

    //when
    final var sequentialFlux = this.service.exploreMergeSequential();

    //then
    StepVerifier.create(sequentialFlux)
        .expectNext("A", "B", "C", "D", "E", "F")
        .verifyComplete();

  }

  @Test
  void shouldCombineTwoFluxUsingZip() {
    //given

    //when
    final var zipFlux = this.service.exploreZip();

    //then
    StepVerifier.create(zipFlux)
        .expectNext("AD", "BE", "CF")
        .verifyComplete();

  }

  @Test
  void shouldCombineTwoFluxUsingZipWith4Fluxes() {
    //given

    //when
    final var zipFlux = this.service.exploreZipWith4Fluxes();

    //then
    StepVerifier.create(zipFlux)
        .expectNext("AD14", "BE25", "CF36")
        .verifyComplete();

  }

  @Test
  void shouldCombineTwoFluxUsingZipWith() {
    //given

    //when
    final var zipFlux = this.service.exploreZipWith();

    //then
    StepVerifier.create(zipFlux)
        .expectNext("AD", "BE", "CF")
        .verifyComplete();

  }

  @Test
  void shouldRaiseAException() {
    //given

    //when
    final Flux<String> stringFlux = this.service.exceptionFlux();

    //then
    StepVerifier.create(stringFlux)
        .expectNext("A", "B", "C")
        .expectError(RuntimeException.class)
        .verify();

  }

  @Test
  void shouldRaiseAExceptionV2() {
    //given

    //when
    final Flux<String> stringFlux = this.service.exceptionFlux();

    //then
    StepVerifier.create(stringFlux)
        .expectNext("A", "B", "C")
        .expectErrorMessage("Exception occurred")
        .verify();

  }

  @Test
  void shouldReturnAFallbackValueWhenExceptionOccurs() {
    //given

    //when
    final Flux<String> stringFlux = this.service.exploreOnErrorReturn();

    //then
    StepVerifier.create(stringFlux)
        .expectNext("A", "B", "C", "D")
        .verifyComplete();

  }

  @Test
  void shouldReturnAFallbackValueWhenMonoExceptionOccurs() {
    //given

    //when
    final Mono<String> stringMono = this.service.exploreMonoOnErrorReturn();

    //then
    StepVerifier.create(stringMono)
        .expectNext("abc")
        .verifyComplete();

  }

  @Test
  void shouldRecoveryValueWhenIllegalStateExceptionOccurs() {
    //given
    final IllegalStateException ex = new IllegalStateException("Not a valid state");
    //when
    final Flux<String> stringFlux = this.service.exploreOnErrorResume(ex);

    //then
    StepVerifier.create(stringFlux)
        .expectNext("A", "B", "C", "D", "E", "F")
        .verifyComplete();

  }

  @Test
  void shouldReturnExceptionWhenExceptionIsNotIllegalState() {
    //given
    final RuntimeException ex = new RuntimeException("Not a valid state");
    //when
    final Flux<String> stringFlux = this.service.exploreOnErrorResume(ex);

    //then
    StepVerifier.create(stringFlux)
        .expectNext("A", "B", "C")
        .expectError(RuntimeException.class)
        .verify();

  }

  @Test
  void shouldContinueTheProcessWhenExceptionOccurs() {
    //given

    //when
    final Flux<String> stringFlux = this.service.exploreOnErrorContinue();

    //then
    StepVerifier.create(stringFlux)
        .expectNext("A", "C", "D")
        .verifyComplete();

  }

  @Test
  void shouldRaiseExceptionWhenInputIsABCInLowerCase() {
    //given
    final String input = "abc";

    //when
    final Mono<String> stringMono = this.service.exploreMonoOnErrorContinue(input);

    //then
    StepVerifier.create(stringMono)
        .expectError(IllegalStateException.class)
        .verify();
  }

  @Test
  void shouldReturnAnInputValueWhenInputIsReactor() {
    //given
    final String input = "reactor";

    //when
    final Mono<String> stringMono = this.service.exploreMonoOnErrorContinue(input);

    //then
    StepVerifier.create(stringMono)
        .expectNext(input)
        .verifyComplete();
  }

  @Test
  void shouldReturnReactorExceptionWhenIllegalStateExceptionOccurs() {
    //given

    //when
    final Flux<String> stringFlux = this.service.exploreOnErrorMap();

    //then
    StepVerifier.create(stringFlux)
        .expectNext("A")
        .expectError(ReactorException.class)
        .verify();

  }

  @Test
  void shouldReturnReactorExceptionWhenMonoIllegalStateExceptionOccurs() {
    //given

    //when
    final Mono<String> stringMono = this.service.exploreMonoOnErrorMap();

    //then
    StepVerifier.create(stringMono)
        .expectError(ReactorException.class)
        .verify();

  }

  @Test
  void shouldReturnAIllegalStateExceptionAndDoOnError() {
    //given

    //when
    final Flux<String> stringFlux = this.service.exploreDoOnError();

    //then
    StepVerifier.create(stringFlux)
        .expectNext("A", "B", "C")
        .expectError(IllegalStateException.class)
        .verify();

  }

}