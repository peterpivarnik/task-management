package com.pp.js.tm.controller;

import com.pp.js.tm.service.BugService;
import com.pp.js.tm.service.dto.BugResponseDto;
import com.pp.js.tm.service.dto.CreateBugDto;
import com.pp.js.tm.service.dto.UpdateBugDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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


  /**
   * Returns bug by bug uid.
   *
   * @param bugUid uid of bug
   * @return found bug
   */
  @GetMapping(value = "/task-management/bug/{bugUid}")
  public ResponseEntity<BugResponseDto> getBugByUid(@PathVariable String bugUid) {
    BugResponseDto bug = bugService.getBug(bugUid);
    return ResponseEntity.ok(bug);
  }

  /**
   * Updates bug with new attributes.
   *
   * @param updateBugDto request holding update info
   * @return updated bug
   */
  @PutMapping(value = "/task-management/bug")
  public ResponseEntity<BugResponseDto> updateBug(@RequestBody UpdateBugDto updateBugDto) {
    BugResponseDto bug = bugService.updateBug(updateBugDto);
    return ResponseEntity.ok(bug);
  }

  /**
   * Delete bug.
   *
   * @param bugUid uid of bug to be deleted
   * @return empty response
   */
  @DeleteMapping(value = "/task-management/bug/{bugUid}")
  public ResponseEntity<Void> deleteBug(@PathVariable String bugUid) {
    bugService.deleteBug(bugUid);
    return ResponseEntity.noContent().build();
  }
}
