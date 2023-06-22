package com.basicstrong.functionalprogramming.section13;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import java.util.Arrays;
import java.util.List;

@Slf4j
public class ListFunctionalOperations {

  private static final String SEPARATOR_LINE = "-----------------";
  private static final String INDUSTRY_ONE = "Hollywood";
  private static final String INDUSTRY_TWO = "Bollywood";

  public static void main(String[] args) {
    List<Movie> movies = Arrays.asList(
        Movie.builder().name("Spotlight").releaseYear(2015).industry(INDUSTRY_ONE).build(),
        Movie.builder().name("Avengers: Infinity War").releaseYear(2018).industry(INDUSTRY_TWO)
            .build(),
        Movie.builder().name("Inception").releaseYear(2010).industry(INDUSTRY_ONE).build(),
        Movie.builder().name("Forest Gump").releaseYear(1994).industry(INDUSTRY_TWO).build(),
        Movie.builder().name("3 Idiots").releaseYear(2009).industry(INDUSTRY_ONE).build()
    );

    //Traversal
    movies.forEach(mov -> log.info(mov.toString()));
    log.info(SEPARATOR_LINE);

    //Sorting
    movies.sort((ob1, ob2) -> ob2.getReleaseYear() - ob1.getReleaseYear());
    movies.forEach(mov -> log.info(mov.toString()));
    log.info(SEPARATOR_LINE);

    //Filtering
    movies.stream()
        .filter(mov -> mov.getIndustry().equalsIgnoreCase("Bollywood"))
        .forEach(mov -> log.info(mov.toString()));
    log.info(SEPARATOR_LINE);

    //Mapping
    movies.stream()
        .map(Movie::getName)
        .forEach(log::info);
    log.info(SEPARATOR_LINE);

    //Recuce
    String moviesString = movies.stream()
        .map(Movie::getName)
        .reduce((mv1, mv2) -> mv1 + " | " + mv2).orElse(StringUtils.EMPTY);
    log.info(moviesString);

  }
}
