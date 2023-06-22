package com.reactive.sec11.assignment;

import com.basicstrong.functionalprogramming.section6.interfaces.Consumer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class SlackMember {
  
  @Getter
  private String name;
  @Setter
  private Consumer<String> messageConsumer;

  public SlackMember(final String name) {
    this.name = name;
  }

  public void receives(final String message) {
    log.info(message);
  }

  public void says(final String message) {
    this.messageConsumer.accept(message);
  }
}
