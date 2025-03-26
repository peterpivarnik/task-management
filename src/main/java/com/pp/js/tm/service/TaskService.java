package com.pp.js.tm.service;

import com.pp.js.tm.entity.Task;
import com.pp.js.tm.repository.TaskRepository;
import com.pp.js.tm.service.dto.TaskResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service providing extending functionality for {@link TaskRepository};
 */
@Service
@Transactional
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  /**
   * Returns paginated response with all tasks.
   *
   * @param pageNumber number of page
   * @param pageSize   size of page
   * @return tasks
   */
  public Page<TaskResponseDto> getAllTasks(Integer pageNumber, Integer pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return taskRepository.findAll(pageable)
                         .map(this::mapToTaskResponse);
  }

  private TaskResponseDto mapToTaskResponse(Task task) {
    TaskResponseDto taskResponseDto = new TaskResponseDto();
    taskResponseDto.setName(task.getName());
    taskResponseDto.setUid(task.getUid());
    taskResponseDto.setType(task.getType());
    return taskResponseDto;
  }
}
