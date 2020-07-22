package de.javamark.issues.rest;

import de.javamark.issues.data.IssueDto;
import de.javamark.issues.data.LogWorkDto;
import de.javamark.issues.services.IssueCommandService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/damage-reports")
public class IssueController {
    private final IssueCommandService damageReportService;

    public IssueController(final IssueCommandService damageReportService) {
        this.damageReportService = damageReportService;
    }

    @PostMapping
    public CompletableFuture<Object> postNewDamageReport(@RequestBody final IssueDto issueDto) {
        return this.damageReportService.addReport(issueDto);
    }

    @PutMapping("/{damageReportAggregateId}/assignee/{assignee}")
    public CompletableFuture<Object> putNewAssignee(@PathVariable final String damageReportAggregateId, @PathVariable final String assignee) {
        return this.damageReportService.setAssignee(damageReportAggregateId, assignee);
    }

    @PutMapping("/{damageReportAggregateId}/logwork")
    public CompletableFuture<Object> logAssigneeWork(@PathVariable final String damageReportAggregateId,
                                                     @RequestBody final LogWorkDto logWorkDto) {
        return this.damageReportService.logWork(damageReportAggregateId, logWorkDto);
    }

    @PutMapping("/{damageReportAggregateId}/close")
    public CompletableFuture<Object> logAssigneeWork(@PathVariable final String damageReportAggregateId) {
        return this.damageReportService.close(damageReportAggregateId);
    }
}
