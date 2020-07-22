package de.javamark.issues.events;

import de.javamark.issues.aggregates.IssueStatus;
import lombok.Getter;

@Getter
public class IssueClosedEvent extends BaseEvent<String> {
    private final IssueStatus status;

    public IssueClosedEvent(final String id, final IssueStatus status) {
        super(id);
        this.status = status;
    }
}
