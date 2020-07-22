package de.javamark.issues.events;

import lombok.Getter;

@Getter
public class IssueCreatedEvent extends BaseEvent<String> {
    private final String damageMessage;
    private final Long vehicleId;

    public IssueCreatedEvent(final String id, final Long vehicleId, final String damageMessage) {
        super(id);
        this.damageMessage = damageMessage;
        this.vehicleId = vehicleId;
    }
}
