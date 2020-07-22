package de.javamark.issues.commands;

import lombok.Getter;

@Getter
public class CloseIssueCommand extends BaseCommand<String> {
    public CloseIssueCommand(final String id) {
        super(id);
    }
}
