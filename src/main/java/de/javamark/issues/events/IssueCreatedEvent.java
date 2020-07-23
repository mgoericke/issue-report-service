package de.javamark.issues.events;

import lombok.Getter;

@Getter
public class IssueCreatedEvent extends BaseEvent<String> {
    private final String title;
    private final String message;

    public IssueCreatedEvent(final String id, final String title, final String message) {
        super(id);
        this.title = title;
        this.message = message;
    }
}
