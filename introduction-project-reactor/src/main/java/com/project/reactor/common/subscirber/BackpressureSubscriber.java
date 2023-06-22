package com.project.reactor.common.subscirber;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

@Slf4j
public class BackpressureSubscriber<T> extends BaseSubscriber<T> {

    @Override
    protected void hookOnSubscribe(final Subscription subscription) {
        log.info("Subscribe happened");
        this.request(2);
    }

    @Override
    protected void hookOnNext(final T value) {
        log.info(value + "received");
        this.request(1);
    }

}
