package com.pp.js.tm.controller;

import com.pp.js.tm.service.TaskManagementUserService;
import com.pp.js.tm.service.dto.CreateUserRequestDto;
import com.pp.js.tm.service.dto.UpdateUserRequestDto;
import com.pp.js.tm.service.dto.UserResponseDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for http resource /task-management/user
 */
@RestController
public class UserController {

  private final TaskManagementUserService taskManagementUserService;

  public UserController(TaskManagementUserService taskManagementUserService) {
    this.taskManagementUserService = taskManagementUserService;
  }

  /**
   * Creates user.
   *
   * @param createUserRequest request for user creation
   * @return created user
   */
  @PostMapping(value = "/task-management/user")
  public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserRequestDto createUserRequest) {
    UserResponseDto user = taskManagementUserService.createUser(createUserRequest);
    return ResponseEntity.ok(user);
  }

  /**
   * Returns all users.
   *
   * @param pageNumber number of page
   * @param pageSize   size of page
   * @return paginated list of users
   */
  @GetMapping(value = "/task-management/user")
  public ResponseEntity<List<UserResponseDto>> getAllUsers(@RequestParam Integer pageNumber,
                                                           @RequestParam Integer pageSize) {
    List<UserResponseDto> content = taskManagementUserService.getAllUsers(pageNumber, pageSize)
                                                             .getContent();
    return ResponseEntity.ok(content);
  }

  /**
   * Get user by uid.
   *
   * @param userUid uid of user
   * @return found user
   */
  @GetMapping(value = "/task-management/user/{userUid}")
  public ResponseEntity<UserResponseDto> getUser(@PathVariable String userUid) {
    UserResponseDto user = taskManagementUserService.getUser(userUid);
    return ResponseEntity.ok(user);
  }

  /**
   * Delete user.
   *
   * @param userUid uid of user to be deleted
   * @return empty response
   */
  @DeleteMapping(value = "/task-management/user/{userUid}")
  public ResponseEntity<Void> deleteUser(@PathVariable String userUid) {
    taskManagementUserService.deleteUser(userUid);
    return ResponseEntity.noContent().build();
  }

  /**
   * Updates user.
   *
   * @param updateUserRequestDto request containing data to update user.
   *
   * @return updated user
   */
  @PutMapping(value = "/task-management/user")
  public ResponseEntity<UserResponseDto> updateUser(@RequestBody UpdateUserRequestDto updateUserRequestDto) {
    UserResponseDto updatedUser = taskManagementUserService.updateUser(updateUserRequestDto);
    return ResponseEntity.ok(updatedUser);
  }
}
