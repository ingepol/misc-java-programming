package com.project.reactor.user;

import com.project.reactor.common.exception.ReactorException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Slf4j
public class TestUsers {
    public static void main(final String[] args) {
        final Flux<String> names = Flux.just("Paul Arenas", "Carlos Gomez", "Andres Restrepo");
        final Flux<User> users = names.map(name -> {
                            final String[] nameSplit = name.split(" ");
                            return User.builder().name(nameSplit[0]).lastName(nameSplit[1]).build();
                        }
                ).filter(user -> user.getLastName().equalsIgnoreCase("Arenas"))
                .doOnNext(user -> {
                    if (Objects.isNull(user.getName())) {
                        throw new ReactorException("The name can be empty");
                    }
                    log.info(user.getCompleteName());
                }).map(user -> {
                    user.setName(user.getLastName().toLowerCase());
                    return user;
                });
        users.subscribe(e -> log.info(e.toString()),
                err -> log.error(err.toString()),
                () -> log.info("The process has finished!")
        );
    }
}
