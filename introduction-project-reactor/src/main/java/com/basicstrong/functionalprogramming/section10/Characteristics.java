package com.basicstrong.functionalprogramming.section10;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Spliterator;
import java.util.stream.Stream;

@Slf4j
public class Characteristics {

  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(4);
    list.add(7);
    list.add(9);
    list.add(0);
    list.add(1);

    Stream<Integer> stream = list.stream();
    Spliterator<Integer> spliterator = stream.spliterator();
    int bits = spliterator.characteristics();
    log.info(String.valueOf(Integer.bitCount(bits)));
    log.info(String.valueOf(Integer.bitCount(bits | 0x00000010)));
    log.info(String.valueOf(Integer.bitCount(bits & 0x00000010)));
    log.info(String.valueOf(spliterator.hasCharacteristics(Spliterator.ORDERED)));

  }
}
