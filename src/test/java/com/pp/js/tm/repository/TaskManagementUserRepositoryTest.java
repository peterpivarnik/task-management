package com.pp.js.tm.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.pp.js.tm.entity.TaskManagementUser;
import com.pp.js.tm.testservice.TestTaskManagementUserService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskManagementUserRepositoryTest {

  @Autowired
  private TaskManagementUserRepository taskManagementUserRepository;

  @Autowired
  private TestTaskManagementUserService testTaskManagementUserService;

  @Test
  void shouldFindByUid() {
    String userUid = testTaskManagementUserService.createUser("firstName", "lastName");

    Optional<TaskManagementUser> foundUser = taskManagementUserRepository.findByUid(userUid);

    assertThat(foundUser).isNotEmpty();
    assertThat(foundUser.get().getUid()).isEqualTo(userUid);
  }
}