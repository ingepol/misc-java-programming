package com.project.reactor.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class User {

    private String name;

    private String lastName;

    public String getCompleteName() {
        return this.name + " " + this.lastName;
    }

}
