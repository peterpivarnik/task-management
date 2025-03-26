package com.pp.js.tm.repository;

import com.pp.js.tm.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@link Task} entity.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>  {
}
