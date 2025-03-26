package com.pp.js.tm.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pp.js.tm.entity.TaskManagementUser;
import com.pp.js.tm.exception.EntityNotFoundException;
import com.pp.js.tm.repository.TaskManagementUserRepository;
import com.pp.js.tm.service.dto.CreateUserRequestDto;
import com.pp.js.tm.service.dto.UpdateUserRequestDto;
import com.pp.js.tm.service.dto.UserResponseDto;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class TaskManagementUserServiceTest {

  @InjectMocks
  private TaskManagementUserService taskManagementUserService;

  @Mock
  private TaskManagementUserRepository taskManagementUserRepository;

  @Test
  void shouldCreateUser() {
    CreateUserRequestDto createUserRequest = getCreateUserRequest();
    when(taskManagementUserRepository.save(any())).thenReturn(createTaskManagementUser());

    UserResponseDto user = taskManagementUserService.createUser(createUserRequest);

    assertThat(user.getFirstName()).isEqualTo("first");
    assertThat(user.getLastName()).isEqualTo("last");
    assertThat(user.getUid()).isEqualTo("Uid");
  }

  private CreateUserRequestDto getCreateUserRequest() {
    CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
    createUserRequestDto.setFirstName("firstName");
    createUserRequestDto.setLastName("lastName");
    return createUserRequestDto;
  }

  @Test
  void shouldReturnAllUsers() {
    when(taskManagementUserRepository.findAll(any(Pageable.class))).thenReturn(createPage());

    Page<UserResponseDto> allUsers = taskManagementUserService.getAllUsers(1, 20);

    assertThat(allUsers.getTotalElements()).isEqualTo(1);
    assertThat(allUsers.getTotalPages()).isEqualTo(1);
    assertThat(allUsers.getSize()).isEqualTo(1);
    assertThat(allUsers.getContent().size()).isEqualTo(1);
    assertThat(allUsers.getContent().getFirst().getFirstName()).isEqualTo("first");
    assertThat(allUsers.getContent().getFirst().getLastName()).isEqualTo("last");
    assertThat(allUsers.getContent().getFirst().getUid()).isEqualTo("Uid");
  }

  private Page<TaskManagementUser> createPage() {
    return new PageImpl<>(createUsers());
  }

  private List<TaskManagementUser> createUsers() {
    return List.of(createTaskManagementUser());
  }

  private TaskManagementUser createTaskManagementUser() {
    TaskManagementUser user = new TaskManagementUser();
    user.setFirstName("first");
    user.setLastName("last");
    user.setUid("Uid");
    return user;
  }

  @Test
  void shouldReturnUser() {
    String uid = "uid";
    when(taskManagementUserRepository.findByUid(uid)).thenReturn(Optional.of(createTaskManagementUser()));

    UserResponseDto user = taskManagementUserService.getUser(uid);

    assertThat(user.getFirstName()).isEqualTo("first");
    assertThat(user.getLastName()).isEqualTo("last");
    assertThat(user.getUid()).isEqualTo("Uid");
  }

  @Test
  void shouldThrowExceptionWhenUserNotExists() {
    String uid = "uid";
    when(taskManagementUserRepository.findByUid(uid)).thenReturn(Optional.empty());

    EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class,
                                                                   () -> taskManagementUserService.getUser(uid));

    assertThat(entityNotFoundException.getMessage()).isEqualTo("User with uid uid not found!");
  }

  @Test
  void shouldDeleteUserWhenUserExists() {
    String uid = "uid";
    TaskManagementUser taskManagementUser = createTaskManagementUser();
    when(taskManagementUserRepository.findByUid(uid)).thenReturn(Optional.of(taskManagementUser));

    taskManagementUserService.deleteUser(uid);

    verify(taskManagementUserRepository).delete(taskManagementUser);
  }

  @Test
  void shouldDoNothingWhenUserNotExists() {
    String uid = "uid";
    when(taskManagementUserRepository.findByUid(uid)).thenReturn(Optional.empty());

    taskManagementUserService.deleteUser(uid);

    verify(taskManagementUserRepository, times(0)).delete(any());
  }

  @Test
  void shouldUpdateUser() {
    UpdateUserRequestDto updateUserRequest = createUpdateUserRequestDto();
    TaskManagementUser taskManagementUser = createTaskManagementUser();
    when(taskManagementUserRepository.findByUid(updateUserRequest.getUid()))
        .thenReturn(Optional.of(taskManagementUser));

    when(taskManagementUserRepository.save(taskManagementUser))
        .thenReturn(taskManagementUser);

    taskManagementUserService.updateUser(updateUserRequest);

    verify(taskManagementUserRepository).save(taskManagementUser);
  }

  private UpdateUserRequestDto createUpdateUserRequestDto() {
    UpdateUserRequestDto updateUserRequestDto = new UpdateUserRequestDto();
    updateUserRequestDto.setUid("uid");
    return updateUserRequestDto;
  }

  @Test
  void shouldThrowExceptionWhenUserForUpdateNotExist() {
    UpdateUserRequestDto updateUserRequest = new UpdateUserRequestDto();
    when(taskManagementUserRepository.findByUid(updateUserRequest.getUid())).thenReturn(Optional.empty());

    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                                                     () -> taskManagementUserService.updateUser(updateUserRequest));

    assertThat(exception.getMessage()).isEqualTo("User with uid null not found!");
  }
}