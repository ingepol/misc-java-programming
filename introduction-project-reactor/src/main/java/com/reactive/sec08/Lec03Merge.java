package com.reactive.sec08;

import com.reactive.sec08.helper.flights.AmericaAirlines;
import com.reactive.sec08.helper.flights.Emirates;
import com.reactive.sec08.helper.flights.Qatar;
import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec03Merge {

  public static void main(final String[] args) {
    final Flux<String> merge = Flux.merge(
        Qatar.getFlights(),
        Emirates.getFlights(),
        AmericaAirlines.getFlights()
    );

    merge.subscribe(subscriber());

    sleepSeconds(10);
  }

}
