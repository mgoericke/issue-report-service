package de.javamark.issues.data;

import de.javamark.issues.aggregates.IssueStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssueDto {

    private Long vehicleId;

    private String damageMessage;

    private String assignee;

    private BigDecimal workLogInHours;

    private IssueStatus status;
}
