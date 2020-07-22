package de.javamark.issues.services;

import java.util.List;

public interface IssueEventService {
    List<Object> listEventsForDamageReport(String reportId);
}
