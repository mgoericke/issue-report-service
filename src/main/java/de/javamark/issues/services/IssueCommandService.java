package de.javamark.issues.services;

import de.javamark.issues.data.AssigneeDto;
import de.javamark.issues.data.IssueDto;
import de.javamark.issues.data.WorkLogDto;

import java.util.concurrent.CompletableFuture;

public interface IssueCommandService {
    CompletableFuture<Object> add(IssueDto issueDto);

    CompletableFuture<Object> assign(String issueAggregateId, AssigneeDto assigneeDto);

    CompletableFuture<Object> logWork(String damageReportAggregateId, WorkLogDto workLogDto);

    CompletableFuture<Object> close(String damageReportAggregateId);

    CompletableFuture<Object> startWorking(String issueAggregateId);
}
