package com.piffner.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BugLikeRepository extends JpaRepository<BugLike, Long> {
}
