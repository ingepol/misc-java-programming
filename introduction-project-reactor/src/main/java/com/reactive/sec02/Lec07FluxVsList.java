package com.reactive.sec02;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.reactive.sec02.helper.NameGenerator.getNamesFlux;
import static com.reactive.sec02.helper.NameGenerator.getNamesList;
import static com.reactive.utils.ReactiveUtility.onNext;

@Slf4j
public class Lec07FluxVsList {

  public static void main(final String[] args) {
    final List<String> names = getNamesList(5);
    log.info(names.toString()); // I have to wait 5 seconds to print the names

    getNamesFlux(5).subscribe(onNext()); // I just wait one second per name to print it.

  }
}
