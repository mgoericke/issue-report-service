package de.javamark.issues.events;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class WorkLoggedEvent extends BaseEvent<String> {
    private final BigDecimal workLogInHours;

    public WorkLoggedEvent(final String id, final BigDecimal workLogInHours) {
        super(id);
        this.workLogInHours = workLogInHours;
    }
}
