package de.javamark.issues.events;

import lombok.Getter;

@Getter
public class AssigneeSetEvent extends BaseEvent<String> {
    private final String assignee;

    public AssigneeSetEvent(final String id, final String assignee) {
        super(id);
        this.assignee = assignee;
    }
}
