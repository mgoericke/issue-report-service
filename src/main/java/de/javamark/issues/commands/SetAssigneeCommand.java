package de.javamark.issues.commands;

import lombok.Getter;

@Getter
public class SetAssigneeCommand extends BaseCommand<String> {
    private final String assignee;

    public SetAssigneeCommand(final String id, final String assignee) {
        super(id);
        this.assignee = assignee;
    }
}
