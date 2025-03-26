package com.pp.js.tm.repository;

import com.pp.js.tm.entity.TaskManagementUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@link TaskManagementUser} entity.
 */
@Repository
public interface TaskManagementUserRepository extends JpaRepository<TaskManagementUser, Integer> {

  Optional<TaskManagementUser> findByUid(String uid);
}
