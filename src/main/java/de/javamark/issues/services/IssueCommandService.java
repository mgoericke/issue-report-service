package de.javamark.issues.services;

import de.javamark.issues.data.IssueDto;
import de.javamark.issues.data.LogWorkDto;

import java.util.concurrent.CompletableFuture;

public interface IssueCommandService {
    CompletableFuture<Object> addReport(IssueDto issueDto);

    CompletableFuture<Object> setAssignee(String damageReportAggregateId, String assignee);

    CompletableFuture<Object> logWork(String damageReportAggregateId, LogWorkDto logWorkDto);

    CompletableFuture<Object> close(String damageReportAggregateId);
}
