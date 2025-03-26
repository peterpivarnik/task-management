package com.pp.js.tm.repository;

import com.pp.js.tm.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@link Bug} entity.
 */
@Repository
public interface BugRepository extends JpaRepository<Bug, Integer> {
}

