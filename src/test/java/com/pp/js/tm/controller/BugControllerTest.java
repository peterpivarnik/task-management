package com.pp.js.tm.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.pp.js.tm.service.dto.BugResponseDto;
import com.pp.js.tm.service.dto.CreateBugDto;
import com.pp.js.tm.service.dto.CreateUserRequestDto;
import com.pp.js.tm.service.dto.UserResponseDto;
import com.pp.js.tm.testservice.TestBugService;
import com.pp.js.tm.testservice.TestTaskManagementUserService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BugControllerTest {

  @LocalServerPort
  protected int port;

  @Autowired
  private TestBugService testBugService;

  @BeforeEach
  void beforeEach() {
    testBugService.deleteAll();
  }


  @Test
  void shouldCreateBug() {
    CreateBugDto request = createBugDto();

    BugResponseDto bugResponseDto = given()
        .port(port)
        .accept(JSON)
        .contentType(JSON)
        .body(request)
        .when()
        .post("/task-management/bug")
        .then()
        .statusCode(200)
        .extract()
        .as(BugResponseDto.class);

    assertThat(bugResponseDto.getName()).isEqualTo(request.getName());
    assertThat(bugResponseDto.getSeverity()).isEqualTo(request.getSeverity());
    assertThat(bugResponseDto.getSteps()).isEqualTo(request.getSteps());
    assertThat(bugResponseDto.getUid()).isNotNull();
    assertThat(bugResponseDto.getCreatedAt()).isNotNull();
  }

  private CreateBugDto createBugDto() {
    CreateBugDto createBugDto = new CreateBugDto();
    createBugDto.setName("bugName");
    createBugDto.setSteps("Steps to reproduce");
    createBugDto.setSeverity("Normal");
    return createBugDto;
  }
}