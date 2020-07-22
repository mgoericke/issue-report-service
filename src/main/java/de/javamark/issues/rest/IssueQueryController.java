package de.javamark.issues.rest;

import de.javamark.issues.data.IssueDto;
import de.javamark.issues.services.IssueQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueQueryController {
    private final IssueQueryService issueQueryService;

    public IssueQueryController(final IssueQueryService issueQueryService) {
        this.issueQueryService = issueQueryService;
    }

    @GetMapping
    public List<IssueDto> listAllIssues() {
        return this.issueQueryService.getAllIssues();
    }

}
