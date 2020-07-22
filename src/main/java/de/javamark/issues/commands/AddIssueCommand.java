package de.javamark.issues.commands;

import lombok.Getter;

@Getter
public class AddIssueCommand extends BaseCommand<String> {

    private final Long vehicleId;
    private final String damageMessage;

    public AddIssueCommand(final String id, final Long vehicleId, final String damageMessage) {
        super(id);
        this.vehicleId = vehicleId;
        this.damageMessage = damageMessage;
    }
}
