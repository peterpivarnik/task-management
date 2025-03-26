package com.pp.js.tm.facade;

import com.pp.js.tm.entity.Task;
import com.pp.js.tm.entity.TaskManagementUser;
import com.pp.js.tm.exception.EntityNotFoundException;
import com.pp.js.tm.mapper.TaskMapper;
import com.pp.js.tm.service.TaskManagementUserService;
import com.pp.js.tm.service.TaskService;
import com.pp.js.tm.service.dto.TaskResponseDto;
import com.pp.js.tm.service.dto.UserResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AssignTaskFacade {

  private final TaskManagementUserService taskManagementUserService;
  private final TaskService taskService;

  public AssignTaskFacade(TaskManagementUserService taskManagementUserService, TaskService taskService) {
    this.taskManagementUserService = taskManagementUserService;
    this.taskService = taskService;
  }

  public TaskResponseDto assignTask(String taskUid, String userUid) {
    Task task = taskService.findByUid(taskUid)
                           .orElseThrow(() -> new EntityNotFoundException(
                               "Task with uid " + taskUid + " not found!"));
    TaskManagementUser taskManagementUser = taskManagementUserService.findByUid(userUid)
                                                                     .orElseThrow(() -> new EntityNotFoundException(
                                                                         "User with uid " + userUid + " not found!"));
    task.setTaskManagementUser(taskManagementUser);
    Task savedTask = taskService.save(task);
    return TaskMapper.mapToTaskResponse(savedTask);
  }
}
