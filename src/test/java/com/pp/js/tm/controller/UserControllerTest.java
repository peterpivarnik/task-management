package com.pp.js.tm.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.pp.js.tm.entity.TaskManagementUser;
import com.pp.js.tm.service.dto.CreateUserRequestDto;
import com.pp.js.tm.service.dto.UpdateUserRequestDto;
import com.pp.js.tm.service.dto.UserResponseDto;
import com.pp.js.tm.testservice.TestTaskManagementUserService;
import io.restassured.http.ContentType;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserControllerTest {

  @LocalServerPort
  protected int port;

  @Autowired
  private TestTaskManagementUserService testTaskManagementUserService;

  @BeforeEach
  void beforeEach() {
    testTaskManagementUserService.deleteAll();
  }

  @Test
  void shouldCreateUser() {
    CreateUserRequestDto request = createCreateUserRequestDto();

    UserResponseDto userResponseDto = given()
        .port(port)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post("/task-management/user")
        .then()
        .statusCode(200)
        .extract()
        .as(UserResponseDto.class);

    assertThat(userResponseDto.getFirstName()).isEqualTo(request.getFirstName());
    assertThat(userResponseDto.getLastName()).isEqualTo(request.getLastName());
    assertThat(userResponseDto.getUid()).isNotNull();
  }

  private CreateUserRequestDto createCreateUserRequestDto() {
    CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
    createUserRequestDto.setFirstName("firstName");
    createUserRequestDto.setLastName("lastName");
    return createUserRequestDto;
  }

  @Test
  void shouldGetAllUsers() {
    testTaskManagementUserService.createUser("testFirstName", "testLastName");

    UserResponseDto[] userResponses = given()
        .port(port)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .when()
        .queryParam("pageNumber", 0)
        .queryParam("pageSize", 10)
        .get("/task-management/user")
        .then()
        .statusCode(200)
        .extract()
        .as(UserResponseDto[].class);

    assertThat(userResponses).hasSize(1);
    assertThat(userResponses[0].getFirstName()).isEqualTo("testFirstName");
    assertThat(userResponses[0].getLastName()).isEqualTo("testLastName");
  }

  @Test
  void shouldGetUserByUid() {
    String userUid = testTaskManagementUserService.createUser("testFirstName", "testLastName");

    UserResponseDto userResponseDto = given()
        .port(port)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .when()
        .pathParam("userUid", userUid)
        .get("/task-management/user/{userUid}")
        .then()
        .statusCode(200)
        .extract()
        .as(UserResponseDto.class);

    assertThat(userResponseDto.getFirstName()).isEqualTo("testFirstName");
    assertThat(userResponseDto.getLastName()).isEqualTo("testLastName");
    assertThat(userResponseDto.getUid()).isEqualTo(userUid);
  }

  @Test
  void shouldDeleteUser() {
    String userUid = testTaskManagementUserService.createUser("testFirstName", "testLastName");

    List<TaskManagementUser> allUsers = testTaskManagementUserService.findAll();
    assertThat(allUsers).hasSize(1);

    given()
        .port(port)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .when()
        .pathParam("userUid", userUid)
        .delete("/task-management/user/{userUid}")
        .then()
        .statusCode(204);

    allUsers = testTaskManagementUserService.findAll();
    assertThat(allUsers).isEmpty();
  }


  @Test
  void shouldUpdateUser() {
    String userUid = testTaskManagementUserService.createUser("testFirstName", "testLastName");
    UpdateUserRequestDto request = createUpdateUserRequestDto(userUid);

    UserResponseDto userResponseDto = given()
        .port(port)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .put("/task-management/user")
        .then()
        .statusCode(200)
        .extract()
        .as(UserResponseDto.class);

    assertThat(userResponseDto.getFirstName()).isEqualTo(request.getFirstName());
    assertThat(userResponseDto.getLastName()).isEqualTo(request.getLastName());
    assertThat(userResponseDto.getUid()).isNotNull();
  }

  private UpdateUserRequestDto createUpdateUserRequestDto(String userUid) {
    UpdateUserRequestDto updateUserRequestDto = new UpdateUserRequestDto();
    updateUserRequestDto.setUid(userUid);
    updateUserRequestDto.setFirstName("newFirstName");
    updateUserRequestDto.setLastName("newLastName");
    return updateUserRequestDto;
  }
}