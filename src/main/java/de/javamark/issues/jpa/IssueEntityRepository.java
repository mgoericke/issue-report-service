package de.javamark.issues.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueEntityRepository extends JpaRepository<IssueEntity, String> {
}
