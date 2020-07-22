package de.javamark.issues.events;

import lombok.Getter;

@Getter
public class BaseEvent<T> {
    private final T id;

    public BaseEvent(final T id) {
        this.id = id;
    }
}
