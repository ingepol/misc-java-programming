package com.basicstrong.functionalprogramming.section6;

import com.basicstrong.functionalprogramming.section6.interfaces.IConfigurator;
import com.basicstrong.functionalprogramming.section6.interfaces.IFactory;
import com.basicstrong.functionalprogramming.section6.interfaces.IProducer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HigherOrderFunctions {

  public static void main(String[] args) {
    IFactory<Integer> factory = createFactory(() -> Math.random() * 1000, Double::intValue);
    Integer create = factory.create();
    log.info(String.valueOf(create));
  }

  public static <T, R> IFactory<R> createFactory(IProducer<T> producer,
      IConfigurator<T, R> configurator) {
    return () -> {
      T product = producer.produce();
      return configurator.configure(product);
    };
  }
}
