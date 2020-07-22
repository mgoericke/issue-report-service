package de.javamark.issues.services;

import java.util.List;

public interface IssueQueryService {
    List<Object> listEventsForDamageReport(String reportId);
}
