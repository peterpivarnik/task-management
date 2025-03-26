package com.pp.js.tm.repository;

import com.pp.js.tm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
