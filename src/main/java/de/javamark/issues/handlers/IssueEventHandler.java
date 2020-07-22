package de.javamark.issues.handlers;

import de.javamark.issues.aggregates.IssueStatus;
import de.javamark.issues.events.AssigneeSetEvent;
import de.javamark.issues.events.IssueClosedEvent;
import de.javamark.issues.events.IssueCreatedEvent;
import de.javamark.issues.events.WorkLoggedEvent;
import de.javamark.issues.jpa.IssueEntity;
import de.javamark.issues.jpa.IssueEntityRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ProcessingGroup("damage-reports")
public class IssueEventHandler {
    private final IssueEntityRepository reportRepository;

    public IssueEventHandler(final IssueEntityRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * adds a new damage report to the view database
     *
     * @param event
     */
    @EventHandler
    public void on(final IssueCreatedEvent event) {
        this.reportRepository.save(IssueEntity.builder()
                .id(event.getId())
                .assignee(null)
                .damageMessage(event.getDamageMessage())
                .vehicleId(event.getVehicleId())
                .workLogInHours(new BigDecimal(0))
                .status(IssueStatus.CREATED)
                .build());
    }

    /**
     * assigns a specific person to a damage report
     *
     * @param event
     */
    @EventHandler
    public void on(final AssigneeSetEvent event) {
        this.reportRepository.findById(event.getId())
                .ifPresent(issueEntity -> {
                    issueEntity.setAssignee(event.getAssignee());
                    issueEntity.setStatus(IssueStatus.ASSIGNED);
                    this.reportRepository.save(issueEntity);
                });
    }

    /**
     * updates the work log by adding hours received from event
     *
     * @param event
     */
    @EventHandler
    public void on(final WorkLoggedEvent event) {
        this.reportRepository.findById(event.getId())
                .ifPresent(issueEntity -> {
                    issueEntity.setWorkLogInHours(issueEntity.getWorkLogInHours().add(event.getWorkLogInHours()));
                    issueEntity.setStatus(IssueStatus.WORK_IN_PROGRESS);
                    this.reportRepository.save(issueEntity);
                });
    }

    /**
     * closes the issue
     *
     * @param event
     */
    @EventHandler
    public void on(final IssueClosedEvent event) {
        this.reportRepository.findById(event.getId())
                .ifPresent(issueEntity -> {
                    issueEntity.setStatus(event.getStatus());
                    this.reportRepository.save(issueEntity);
                });
    }
}
