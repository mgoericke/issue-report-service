package de.javamark.issues.aggregates;

import de.javamark.issues.commands.*;
import de.javamark.issues.events.*;
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

    private String message;

    private String assignee;

    private BigDecimal workLogInHours;

    private IssueStatus status = IssueStatus.PENDING;

    private boolean closed = false;

    private boolean workInProgress = false;

    @CommandHandler
    public Issue(final OpenIssueCommand command) {
        apply(new IssueCreatedEvent(
                command.getId(),
                command.getMessage()
        ));
    }

    @EventSourcingHandler
    protected void on(final IssueCreatedEvent event) {
        this.id = event.getId();
        this.message = event.getMessage();
        log.info("[+] on IssueCreatedEvent {}", event.getId());
    }

    @CommandHandler
    protected void on(final SetAssigneeCommand command) {
        if (this.closed) {
            throw new IllegalStateException("[+] issue has already been closed");
        }
        apply(new AssigneeSetEvent(command.getId(), command.getAssignee()));
    }

    @EventSourcingHandler
    protected void on(final AssigneeSetEvent event) {
        this.assignee = event.getAssignee();
        log.info("[+] on AssigneeSetEvent {}", event.getId());
    }


    @CommandHandler
    protected void on(final StartWorkCommand command) {
        if (this.closed) {
            throw new IllegalStateException("[+] issue has already been closed");
        }
        apply(new WorkStartedEvent(command.getId(), IssueStatus.WORK_IN_PROGRESS));
    }

    @EventSourcingHandler
    protected void on(final WorkStartedEvent event) {
        if (this.closed) {
            this.closed = false;
        }
        this.workInProgress = true;
        log.info("[+] on WorkStartedEvent {}", event.getId());
    }

    @CommandHandler
    protected void on(final LogWorkCommand command) {
        // users can log work even after the issue was closed
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
        if (this.closed) {
            throw new IllegalStateException("[+] cannot close issue - issue has already been closed");
        }
        if (!this.workInProgress) {
            throw new IllegalStateException("[+] cannot close issue - work hasn't started yet");
        }
        apply(new IssueClosedEvent(
                command.getId(),
                IssueStatus.FIXED));
    }

    @EventSourcingHandler
    protected void on(final IssueClosedEvent event) {
        this.status = event.getStatus();
        this.closed = true;
        log.info("[+] on IssueClosedEvent {}", event.getId());
    }
}
