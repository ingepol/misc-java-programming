package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

@Slf4j
@SuppressWarnings("unused")
public class BoundedStream {

  public static void main(String[] args) {
    //1. stream() on a collection
    List<Integer> list = List.of(1, 4, 7, 9, 4);
    Stream<Integer> stream = list.stream();

    //How we can stream a map
    Map<Integer, String> map = Map.of(1, "one", 2, "two", 3, "three", 4, "four");

    Stream<String> values = map.values().stream();
    Stream<Integer> keys = map.keySet().stream();

    //2. of() Stream class
    Stream<String> streamString = Stream.of("Hey!", "Happy", "Thanks giving");

    //3. stream() of Arrays Class
    Integer[] integerArr = {3, 5, 7, 89, 9};
    Stream<Integer> streamInt = Arrays.stream(integerArr);

    int[] intArr = {3, 5, 7, 89, 9};
    IntStream streamInt2 = Arrays.stream(intArr);

    //4. build() os Steam class
    Builder<Integer> builder = Stream.builder();
    builder.add(1);
    builder.add(4);
    Stream<Integer> buildStream = builder.build();


  }
}
