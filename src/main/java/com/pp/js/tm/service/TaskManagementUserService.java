package com.pp.js.tm.service;

import com.pp.js.tm.entity.TaskManagementUser;
import com.pp.js.tm.exception.EntityNotFoundException;
import com.pp.js.tm.repository.TaskManagementUserRepository;
import com.pp.js.tm.service.dto.CreateUserRequestDto;
import com.pp.js.tm.service.dto.UpdateUserRequestDto;
import com.pp.js.tm.service.dto.UserResponseDto;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service providing extended functionality for {@link TaskManagementUserRepository}
 */
@Service
@Transactional
public class TaskManagementUserService {

  private final TaskManagementUserRepository taskManagementUserRepository;

  public TaskManagementUserService(TaskManagementUserRepository taskManagementUserRepository) {
    this.taskManagementUserRepository = taskManagementUserRepository;
  }

  public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
    TaskManagementUser user = mapUser(createUserRequestDto);
    TaskManagementUser savedUser = taskManagementUserRepository.save(user);
    return mapToUserDto(savedUser);
  }

  private TaskManagementUser mapUser(CreateUserRequestDto createUserRequestDto) {
    TaskManagementUser user = new TaskManagementUser();
    user.setFirstName(createUserRequestDto.getFirstName());
    user.setLastName(createUserRequestDto.getLastName());
    return user;
  }

  private UserResponseDto mapToUserDto(TaskManagementUser savedUser) {
    UserResponseDto userResponseDto = new UserResponseDto();
    userResponseDto.setUid(savedUser.getUid());
    userResponseDto.setFirstName(savedUser.getFirstName());
    userResponseDto.setLastName(savedUser.getLastName());
    return userResponseDto;
  }

  public Page<UserResponseDto> getAllUsers(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return taskManagementUserRepository.findAll(pageable)
                                       .map(this::mapToUserDto);
  }

  public UserResponseDto getUser(String uid) {
    return findByUid(uid)
        .map(this::mapToUserDto)
        .orElseThrow(
            () -> new EntityNotFoundException("User with uid " + uid + " not found!"));

  }

  public void deleteUser(String uid) {
    findByUid(uid)
        .ifPresent(taskManagementUserRepository::delete);
  }

  public UserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto) {
    return findByUid(updateUserRequestDto.getUid())
        .map(user -> updateUserEntity(user, updateUserRequestDto))
        .map(this::mapToUserDto)
        .orElseThrow(() -> new EntityNotFoundException(
            "User with uid " + updateUserRequestDto.getUid() + " not found!"));
  }

  private TaskManagementUser updateUserEntity(TaskManagementUser user, UpdateUserRequestDto updateUserRequestDto) {
    user.setFirstName(updateUserRequestDto.getFirstName());
    user.setLastName(updateUserRequestDto.getLastName());
    return taskManagementUserRepository.save(user);
  }

  public Optional<TaskManagementUser> findByUid(String uid) {
    return taskManagementUserRepository.findByUid(uid);
  }
}
