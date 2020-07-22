package de.javamark.issues.handlers;

import de.javamark.issues.data.IssueDto;
import de.javamark.issues.jpa.IssueEntityRepository;
import de.javamark.issues.queries.FindAllIssuesQuery;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@ProcessingGroup("issue-queries")
public class IssueQueryHandler {

    private final IssueEntityRepository repository;

    public IssueQueryHandler(final IssueEntityRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<IssueDto> handle(final FindAllIssuesQuery query) {
        return this.repository.findAll().stream()
                .map(issueEntity -> IssueDto.builder()
                        .assignee(issueEntity.getAssignee())
                        .message(issueEntity.getMessage())
                        .workLogInHours(issueEntity.getWorkLogInHours())
                        .status(issueEntity.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
