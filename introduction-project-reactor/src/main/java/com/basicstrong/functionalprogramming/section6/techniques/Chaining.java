package com.basicstrong.functionalprogramming.section6.techniques;

import com.basicstrong.functionalprogramming.section6.interfaces.Consumer;
import lombok.extern.slf4j.Slf4j;

import java.util.function.IntUnaryOperator;

@Slf4j
public class Chaining {

  public static void main(String[] args) {

    Consumer<String> con1 = log::info;
    Consumer<String> con2 = log::info;
    con1.accept("Hello");
    con2.accept("Hello");
    Consumer<String> con3 = s -> {
      con1.accept(s);
      con2.accept(s);
    };
    con3.accept("Hello");

    // Chaining Used to simplify when multiple function are applied in a row one after the other
    Consumer<String> con4 = con1.thenAccept(con2);
    con4.accept("BasicStrong");

    IntUnaryOperator fn1 = s -> s + 2;
    IntUnaryOperator fn2 = s -> s * 2;
    IntUnaryOperator fn3 = fn1.andThen(fn2);
    log.info(String.valueOf(fn3.applyAsInt(10)));

  }
}
