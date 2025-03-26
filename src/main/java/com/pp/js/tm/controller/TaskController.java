package com.pp.js.tm.controller;

import com.pp.js.tm.service.TaskService;
import com.pp.js.tm.service.dto.TaskResponseDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for http resource /task-management/task
 */
@RestController
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  /**
   * Returns all tasks.
   *
   * @param pageNumber number of page
   * @param pageSize   size of page
   * @return paginated list of tasks
   */
  @GetMapping(value = "/task-management/task")
  public ResponseEntity<List<TaskResponseDto>> getAllTasks(@RequestParam Integer pageNumber,
                                                           @RequestParam Integer pageSize) {
    List<TaskResponseDto> content = taskService.getAllTasks(pageNumber, pageSize)
                                               .getContent();
    return ResponseEntity.ok(content);
  }
}
