package com.reactive.sec04;

import com.reactive.sec04.helper.Person;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.function.Function;

import static com.reactive.utils.ReactiveUtility.subscriber;
import static java.util.Objects.requireNonNull;

@Slf4j
public class Lec11SwitchOnFirst {

  public static void main(final String[] args) {
    getPerson()
        .switchOnFirst((signal, personFlux) -> {
          log.info("inside switch-on-first");
          return signal.isOnNext() && requireNonNull(signal.get()).getAge() > 10 ? personFlux
              : applyFilterMap().apply(personFlux);
        })
        //.transform(applyFilterMap())
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
