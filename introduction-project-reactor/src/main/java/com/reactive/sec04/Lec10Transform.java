package com.reactive.sec04;

import com.reactive.sec04.helper.Person;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.function.Function;

import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec10Transform {

  public static void main(final String[] args) {
    getPerson()
        .transform(applyFilterMap())
        .subscribe(subscriber());
  }

  public static Flux<Person> getPerson() {
    return Flux.range(1, 10)
        .map(i -> new Person());
  }

  public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
    return flux -> flux.filter(p -> p.getAge() > 10)
        .doOnNext(p -> p.setName(p.getName().toUpperCase()))
        .doOnDiscard(Person.class, p -> log.warn("Not allowing {}", p));
  }
}
