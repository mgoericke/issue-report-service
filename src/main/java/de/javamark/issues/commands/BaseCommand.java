package de.javamark.issues.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
class BaseCommand<T> {

    @TargetAggregateIdentifier
    private final T id;

    public BaseCommand(final T id) {
        this.id = id;
    }
}
