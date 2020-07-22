package de.javamark.issues.rest;

import de.javamark.issues.data.AssigneeDto;
import de.javamark.issues.data.IssueDto;
import de.javamark.issues.data.WorkLogDto;
import de.javamark.issues.services.IssueCommandService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/issues")
public class IssueCommandController {
    private final IssueCommandService issueCommandService;

    public IssueCommandController(final IssueCommandService issueCommandService) {
        this.issueCommandService = issueCommandService;
    }

    @PostMapping
    public CompletableFuture<Object> postNewIssue(@RequestBody final IssueDto issueDto) {
        return this.issueCommandService.add(issueDto);
    }

    @PutMapping("/{issueAggregateId}/assignee")
    public CompletableFuture<Object> putNewAssignee(@PathVariable final String issueAggregateId,
                                                    @RequestBody final AssigneeDto assigneeDto) {
        return this.issueCommandService.assign(issueAggregateId, assigneeDto);
    }

    @PutMapping("/{issueAggregateId}/start")
    public CompletableFuture<Object> putStartOfWork(@PathVariable final String issueAggregateId) {
        return this.issueCommandService.startWorking(issueAggregateId);
    }

    @PutMapping("/{issueAggregateId}/logwork")
    public CompletableFuture<Object> logAssigneeWork(@PathVariable final String issueAggregateId,
                                                     @RequestBody final WorkLogDto workLogDto) {
        return this.issueCommandService.logWork(issueAggregateId, workLogDto);
    }

    @PutMapping("/{issueAggregateId}/close")
    public CompletableFuture<Object> logAssigneeWork(@PathVariable final String issueAggregateId) {
        return this.issueCommandService.close(issueAggregateId);
    }
}
