package de.javamark.issues.commands;

import lombok.Getter;

@Getter
public class OpenIssueCommand extends BaseCommand<String> {
    private final String title;
    private final String message;

    public OpenIssueCommand(final String id, final String title, final String message) {
        super(id);
        this.title = title;
        this.message = message;
    }
}
