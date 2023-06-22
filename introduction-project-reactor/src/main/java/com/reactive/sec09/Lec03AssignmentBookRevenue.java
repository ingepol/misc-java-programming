package com.reactive.sec09;

import com.reactive.sec09.helper.BookOrder;
import com.reactive.sec09.helper.RevenueReport;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec03AssignmentBookRevenue {

  private static final Long revenue = 0L;

  public static void main(final String[] args) {
    final Set<String> allowCategories = Set.of(
        "Science fiction",
        "Fantasy",
        "Suspense/Thriller"
    );

    getBooksStreams()
        .filter(book -> allowCategories.contains(book.getCategory()))
        .buffer(Duration.ofSeconds(5))
        .doOnNext(books -> log.info(books.toString()))
        .map(Lec03AssignmentBookRevenue::revenueCalculator)
        .subscribe(subscriber());
    sleepSeconds(60);
  }

  private static RevenueReport revenueCalculator(final List<BookOrder> books) {
    final Map<String, Double> map = books.stream()
        .collect(Collectors.groupingBy(
            BookOrder::getCategory,
            Collectors.summingDouble(BookOrder::getPrice)
        ));
    return new RevenueReport(map);
  }

  private static Flux<BookOrder> getBooksStreams() {
    return Flux.interval(Duration.ofMillis(200))
        .map(i -> new BookOrder());
  }

}
