package de.javamark.issues.handlers;

import de.javamark.issues.aggregates.IssueStatus;
import de.javamark.issues.events.*;
import de.javamark.issues.jpa.IssueEntity;
import de.javamark.issues.jpa.IssueEntityRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ProcessingGroup("issue-events")
public class IssueEventHandler {
    private final IssueEntityRepository issueEntityRepository;

    public IssueEventHandler(final IssueEntityRepository issueEntityRepository) {
        this.issueEntityRepository = issueEntityRepository;
    }

    /**
     * adds a new damage report to the view database
     *
     * @param event
     */
    @EventHandler
    public void on(final IssueCreatedEvent event) {
        this.issueEntityRepository.save(IssueEntity.builder()
                .id(event.getId())
                .assignee(null)
                .title(event.getTitle())
                .message(event.getMessage())
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
        this.issueEntityRepository.findById(event.getId())
                .ifPresent(issueEntity -> {
                    issueEntity.setAssignee(event.getAssignee());
                    issueEntity.setStatus(IssueStatus.ASSIGNED);
                    this.issueEntityRepository.save(issueEntity);
                });
    }

    /**
     * marks the start of progress
     *
     * @param event
     */
    @EventHandler
    public void on(final WorkStartedEvent event) {
        this.issueEntityRepository.findById(event.getId())
                .ifPresent(issueEntity -> {
                    issueEntity.setStatus(event.getStatus());
                    this.issueEntityRepository.save(issueEntity);
                });
    }

    /**
     * updates the work log by adding hours received from event
     *
     * @param event
     */
    @EventHandler
    public void on(final WorkLoggedEvent event) {
        this.issueEntityRepository.findById(event.getId())
                .ifPresent(issueEntity -> {
                    issueEntity.setWorkLogInHours(issueEntity.getWorkLogInHours().add(event.getWorkLogInHours()));
                    this.issueEntityRepository.save(issueEntity);
                });
    }

    /**
     * closes the issue
     *
     * @param event
     */
    @EventHandler
    public void on(final IssueClosedEvent event) {
        this.issueEntityRepository.findById(event.getId())
                .ifPresent(issueEntity -> {
                    issueEntity.setStatus(event.getStatus());
                    this.issueEntityRepository.save(issueEntity);
                });
    }

}
