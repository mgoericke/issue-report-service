package de.javamark.issues.events;

import de.javamark.issues.aggregates.IssueStatus;
import lombok.Getter;

@Getter
public class WorkStartedEvent extends BaseEvent<String> {
    private final IssueStatus status;

    public WorkStartedEvent(final String id, final IssueStatus status) {
        super(id);
        this.status = status;
    }
}
