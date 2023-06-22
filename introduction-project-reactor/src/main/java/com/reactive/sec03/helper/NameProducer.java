package com.reactive.sec03.helper;

import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

import static com.reactive.utils.FakerUtility.faker;

public class NameProducer implements Consumer<FluxSink<String>> {

  private FluxSink<String> fluxSink;

  @Override
  public void accept(final FluxSink<String> stringFluxSink) {
    this.fluxSink = stringFluxSink;
  }

  public void producer() {
    final String name = faker().name().fullName();
    final String thread = Thread.currentThread().getName();
    this.fluxSink.next(String.format("%s : %s", thread, name));
  }

}
