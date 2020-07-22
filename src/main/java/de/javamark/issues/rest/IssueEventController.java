package de.javamark.issues.rest;

import de.javamark.issues.services.IssueEventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueEventController {

    private final IssueEventService issueEventService;

    public IssueEventController(final IssueEventService issueEventService) {
        this.issueEventService = issueEventService;
    }

    @GetMapping("/{damageReportAggregateId}/events")
    public List<Object> listEventsForDamageReport(@PathVariable final String damageReportAggregateId) {
        return this.issueEventService.listEventsForDamageReport(damageReportAggregateId);
    }
}
