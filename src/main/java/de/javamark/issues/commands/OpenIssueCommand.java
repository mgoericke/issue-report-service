package de.javamark.issues.commands;

import lombok.Getter;

@Getter
public class OpenIssueCommand extends BaseCommand<String> {
    private final String message;

    public OpenIssueCommand(final String id, final String message) {
        super(id);
        this.message = message;
    }
}
