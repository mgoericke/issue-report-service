package de.javamark.issues.services;

import de.javamark.issues.commands.*;
import de.javamark.issues.data.AssigneeDto;
import de.javamark.issues.data.IssueDto;
import de.javamark.issues.data.WorkLogDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class IssueCommandServiceImpl implements IssueCommandService {

    private final CommandGateway commandGateway;

    public IssueCommandServiceImpl(final CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<Object> add(final IssueDto issueDto) {
        return this.commandGateway.send(new OpenIssueCommand(
                UUID.randomUUID().toString(),
                issueDto.getMessage())
        );
    }

    @Override
    public CompletableFuture<Object> assign(final String issueAggregateId, final AssigneeDto assigneeDto) {
        return this.commandGateway.send(
                new SetAssigneeCommand(
                        issueAggregateId,
                        assigneeDto.getName())
        );
    }

    @Override
    public CompletableFuture<Object> logWork(final String issueAggregateId, final WorkLogDto workLogDto) {
        return this.commandGateway.send(
                new LogWorkCommand(
                        issueAggregateId,
                        workLogDto.getHours())
        );
    }

    @Override
    public CompletableFuture<Object> close(final String damageReportAggregateId) {
        return this.commandGateway.send(
                new CloseIssueCommand(damageReportAggregateId)
        );
    }

    @Override
    public CompletableFuture<Object> startWorking(final String issueAggregateId) {
        return this.commandGateway.send(new StartWorkCommand(issueAggregateId));
    }
}
