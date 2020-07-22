package de.javamark.issues.services;

import de.javamark.issues.data.IssueDto;
import de.javamark.issues.queries.FindAllIssuesQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueQueryServiceImpl implements IssueQueryService {

    private final QueryGateway queryGateway;

    public IssueQueryServiceImpl(final QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public List<IssueDto> getAllIssues() {
        return this.queryGateway.query(
                new FindAllIssuesQuery(),
                ResponseTypes.multipleInstancesOf(IssueDto.class))
                .join();
    }
}
