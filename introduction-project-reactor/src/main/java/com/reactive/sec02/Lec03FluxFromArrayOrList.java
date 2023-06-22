package com.reactive.sec02;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

import static com.reactive.utils.ReactiveUtility.onNext;

public class Lec03FluxFromArrayOrList {

  public static void main(final String[] args) {
    final List<String> strings = Arrays.asList("a", "b", "c");
    Flux.fromIterable(strings).subscribe(onNext());

    final Integer[] arr = {1, 2, 3};
    Flux.fromArray(arr).subscribe(onNext());

  }
}
