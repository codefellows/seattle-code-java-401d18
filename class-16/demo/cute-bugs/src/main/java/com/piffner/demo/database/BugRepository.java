package com.piffner.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * To create a repository, you extend the CrudRepository
 * And tell it the type of table, and the type of ID column
 * <p>
 * Everything else is magic!
 */
public interface BugRepository extends JpaRepository<Bug, Long> {
}
