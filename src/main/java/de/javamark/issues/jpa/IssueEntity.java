package de.javamark.issues.jpa;

import de.javamark.issues.aggregates.IssueStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "damage_report_view")
public class IssueEntity {
    @Id
    private String id;

    @Column
    private String title;
    
    @Column
    private String message;

    @Column
    private String assignee;

    @Column
    private BigDecimal workLogInHours;

    @Column
    @Enumerated(EnumType.STRING)
    private IssueStatus status = IssueStatus.PENDING;

}
