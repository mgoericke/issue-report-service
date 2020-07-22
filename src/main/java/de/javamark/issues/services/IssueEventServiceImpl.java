package de.javamark.issues.services;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueEventServiceImpl implements IssueEventService {
    private final EventStore eventStore;

    public IssueEventServiceImpl(final EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public List<Object> listEventsForDamageReport(final String reportId) {
        return this.eventStore.readEvents(reportId)
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }
}
