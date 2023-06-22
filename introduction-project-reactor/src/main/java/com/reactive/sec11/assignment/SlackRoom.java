package com.reactive.sec11.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class SlackRoom {

  private final String name;
  private final Sinks.Many<SlackMessage> sink;
  private final Flux<SlackMessage> flux;

  public SlackRoom(final String name) {
    this.name = name;
    this.sink = Sinks.many().replay().all();
    this.flux = this.sink.asFlux();
  }

  public void joinRoom(final SlackMember member) {
    log.info("{} -----------  Joined ---------------- {}", member.getName(), this.name);
    this.subscribe(member);
    member.setMessageConsumer(msg -> this.postMessage(msg, member));
  }

  private void subscribe(final SlackMember slackMember) {
    this.flux
        .filter(sm -> !sm.getSender().equals(slackMember.getName()))
        .doOnNext(sm -> sm.setReceiver(slackMember.getName()))
        .map(SlackMessage::toString)
        .subscribe(slackMember::receives);
  }

  private void postMessage(final String msg, final SlackMember member) {
    final SlackMessage slackMessage = SlackMessage.builder()
        .sender(member.getName())
        .message(msg)
        .build();
    this.sink.tryEmitNext(slackMessage);
  }

}
