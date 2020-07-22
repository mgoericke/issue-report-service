package de.javamark.issues.services;

import de.javamark.issues.data.IssueDto;

import java.util.List;

public interface IssueQueryService {
    List<IssueDto> getAllIssues();
}
