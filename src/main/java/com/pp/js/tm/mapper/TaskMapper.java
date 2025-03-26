package com.pp.js.tm.mapper;

import com.pp.js.tm.entity.Task;
import com.pp.js.tm.entity.TaskManagementUser;
import com.pp.js.tm.service.dto.TaskResponseDto;
import com.pp.js.tm.service.dto.UserResponseDto;

public class TaskMapper {

  public static TaskResponseDto mapToTaskResponse(Task task) {
    TaskResponseDto taskResponseDto = new TaskResponseDto();
    taskResponseDto.setName(task.getName());
    taskResponseDto.setUid(task.getUid());
    taskResponseDto.setType(task.getType());
    taskResponseDto.setUser(mapToUserDto(task.getTaskManagementUser()));
    return taskResponseDto;
  }

  private static UserResponseDto mapToUserDto(TaskManagementUser savedUser) {
    if (savedUser == null) {
      return null;
    }
    UserResponseDto userResponseDto = new UserResponseDto();
    userResponseDto.setUid(savedUser.getUid());
    userResponseDto.setFirstName(savedUser.getFirstName());
    userResponseDto.setLastName(savedUser.getLastName());
    return userResponseDto;
  }
}
