package com.pp.js.tm.controller;

import com.pp.js.tm.service.BugService;
import com.pp.js.tm.service.dto.BugResponseDto;
import com.pp.js.tm.service.dto.CreateBugDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for http resource /task-management/bug
 */
@RestController
public class BugController {

  private final BugService bugService;

  public BugController(BugService bugService) {
    this.bugService = bugService;
  }

  /**
   * Creates bug.
   *
   * @param createBugDto request for bug creation
   * @return created bug
   */
  @PostMapping(value = "/task-management/bug")
  public ResponseEntity<BugResponseDto> createBug(@RequestBody CreateBugDto createBugDto) {
    BugResponseDto bug = bugService.createBug(createBugDto);
    return ResponseEntity.ok(bug);
  }
}
