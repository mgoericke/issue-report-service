package de.javamark.issues.rest;

import de.javamark.issues.services.IssueQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/damage-reports")
public class IssueEventController {

    private final IssueQueryService issueQueryService;

    public IssueEventController(final IssueQueryService issueQueryService) {
        this.issueQueryService = issueQueryService;
    }

    @GetMapping("/{damageReportAggregateId}/events")
    public List<Object> listEventsForDamageReport(@PathVariable final String damageReportAggregateId) {
        return this.issueQueryService.listEventsForDamageReport(damageReportAggregateId);
    }
}
