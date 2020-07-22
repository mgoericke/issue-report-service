package de.javamark.issues.events;

import lombok.Getter;

@Getter
public class IssueCreatedEvent extends BaseEvent<String> {
    private final String message;

    public IssueCreatedEvent(final String id, final String message) {
        super(id);
        this.message = message;
    }
}
