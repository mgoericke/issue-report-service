package de.javamark.issues.services;

import de.javamark.issues.commands.AddIssueCommand;
import de.javamark.issues.commands.CloseIssueCommand;
import de.javamark.issues.commands.LogWorkCommand;
import de.javamark.issues.commands.SetAssigneeCommand;
import de.javamark.issues.data.IssueDto;
import de.javamark.issues.data.LogWorkDto;
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
    public CompletableFuture<Object> addReport(final IssueDto issueDto) {
        return this.commandGateway.send(new AddIssueCommand(
                UUID.randomUUID().toString(),
                issueDto.getVehicleId(),
                issueDto.getDamageMessage())
        );
    }

    @Override
    public CompletableFuture<Object> setAssignee(final String damageReportAggregateId, final String assignee) {
        return this.commandGateway.send(
                new SetAssigneeCommand(
                        damageReportAggregateId,
                        assignee)
        );
    }

    @Override
    public CompletableFuture<Object> logWork(final String damageReportAggregateId, final LogWorkDto logWorkDto) {
        return this.commandGateway.send(
                new LogWorkCommand(
                        damageReportAggregateId,
                        logWorkDto.getHours())
        );
    }

    @Override
    public CompletableFuture<Object> close(final String damageReportAggregateId) {
        return this.commandGateway.send(
                new CloseIssueCommand(damageReportAggregateId)
        );
    }
}
