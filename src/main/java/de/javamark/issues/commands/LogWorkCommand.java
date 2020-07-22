package de.javamark.issues.commands;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class LogWorkCommand extends BaseCommand<String> {
    private final BigDecimal workLogInHours;

    public LogWorkCommand(final String id, final BigDecimal workLogInHours) {
        super(id);
        this.workLogInHours = workLogInHours;
    }
}
