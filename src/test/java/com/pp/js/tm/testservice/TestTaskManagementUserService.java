package com.pp.js.tm.testservice;

import com.pp.js.tm.entity.TaskManagementUser;
import com.pp.js.tm.repository.TaskManagementUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TestTaskManagementUserService {

  private final TaskManagementUserRepository taskManagementUserRepository;

  public TestTaskManagementUserService(TaskManagementUserRepository taskManagementUserRepository) {
    this.taskManagementUserRepository = taskManagementUserRepository;
  }

  public String createUser(String firstName, String lastName){
    TaskManagementUser user = new TaskManagementUser();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    return taskManagementUserRepository.save(user)
                                       .getUid();
  }
}
