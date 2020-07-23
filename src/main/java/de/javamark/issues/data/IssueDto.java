package de.javamark.issues.data;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueDto {

    private String id;

    private String title;
    
    private String message;

    private String assignee;

    private BigDecimal workLogInHours;

    private IssueStatus status;
}
