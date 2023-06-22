package com.reactive.sec08;

import com.reactive.sec08.helper.NameGenerator;
import lombok.extern.slf4j.Slf4j;

import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec01StartWith {

  public static void main(final String[] args) {
    final NameGenerator generator = new NameGenerator();
    generator.generateNames()
        .take(2)
        .subscribe(subscriber("Sam"));

    generator.generateNames()
        .take(2)
        .subscribe(subscriber("Mike"));

    generator.generateNames()
        .take(3)
        .subscribe(subscriber("Jake"));

    generator.generateNames()
        .filter(n -> n.startsWith("A"))
        .take(2)
        .subscribe(subscriber("Marshal"));

  }
}
