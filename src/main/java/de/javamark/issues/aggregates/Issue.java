package de.javamark.issues.aggregates;

import de.javamark.issues.commands.AddIssueCommand;
import de.javamark.issues.commands.CloseIssueCommand;
import de.javamark.issues.commands.LogWorkCommand;
import de.javamark.issues.commands.SetAssigneeCommand;
import de.javamark.issues.events.AssigneeSetEvent;
import de.javamark.issues.events.IssueClosedEvent;
import de.javamark.issues.events.IssueCreatedEvent;
import de.javamark.issues.events.WorkLoggedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@NoArgsConstructor
@Aggregate
@Data
@Slf4j
public class Issue {

    @AggregateIdentifier
    private String id;

    private Long vehicleId;

    private String damageMessage;

    private String assignee;

    private BigDecimal workLogInHours;

    private IssueStatus status;

    private boolean closed = false;

    @CommandHandler
    public Issue(final AddIssueCommand command) {
        apply(new IssueCreatedEvent(
                command.getId(),
                command.getVehicleId(),
                command.getDamageMessage()
        ));
    }

    @EventSourcingHandler
    protected void on(final IssueCreatedEvent event) {
        this.id = event.getId();
        this.damageMessage = event.getDamageMessage();
        this.vehicleId = event.getVehicleId();
        log.info("[+] on DamageReportCreatedEvent {}", event.getId());
    }

    @CommandHandler
    protected void on(final SetAssigneeCommand command) {
        if (this.closed) {
            throw new IllegalStateException("[+] damage report is already closed");
        }
        apply(new AssigneeSetEvent(command.getId(), command.getAssignee()));

    }

    @EventSourcingHandler
    protected void on(final AssigneeSetEvent event) {
        this.assignee = event.getAssignee();
        log.info("[+] on AssigneeSetEvent {}", event.getId());
    }

    @CommandHandler
    protected void on(final LogWorkCommand command) {
        if (this.closed) {
            throw new IllegalStateException("[+] damage report is already closed");
        }
        apply(new WorkLoggedEvent(command.getId(),
                command.getWorkLogInHours()));
    }

    @EventSourcingHandler
    protected void on(final WorkLoggedEvent event) {
        if (this.workLogInHours == null) {
            this.workLogInHours = new BigDecimal(0);
        }
        this.workLogInHours = this.workLogInHours.add(event.getWorkLogInHours());
        log.info("[+] on WorkLoggedEvent {}", event.getId());
    }

    @CommandHandler
    protected void on(final CloseIssueCommand command) {
        apply(new IssueClosedEvent(
                command.getId(),
                IssueStatus.FIXED));
    }

    @EventSourcingHandler
    protected void on(final IssueClosedEvent event) {
        this.status = event.getStatus();
        this.closed = true;
        log.info("[+] on DamageReportClosedEvent {}", event.getId());
    }
}
