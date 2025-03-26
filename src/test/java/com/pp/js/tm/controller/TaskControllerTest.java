package com.pp.js.tm.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.pp.js.tm.service.dto.TaskResponseDto;
import com.pp.js.tm.testservice.TestBugService;
import com.pp.js.tm.testservice.TestFeatureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class TaskControllerTest {

  @LocalServerPort
  protected int port;

  @Autowired
  private TestFeatureService testFeatureService;

  @Autowired
  private TestBugService testBugService;

  @BeforeEach
  void beforeEach() {
    testFeatureService.deleteAll();
    testBugService.deleteAll();
  }

  @Test
  void shouldGetAllTasks() {
    testFeatureService.createFeature();

    TaskResponseDto[] tasks = given()
        .port(port)
        .accept(JSON)
        .contentType(JSON)
        .when()
        .queryParam("pageNumber", 0)
        .queryParam("pageSize", 10)
        .get("/task-management/task")
        .then()
        .statusCode(200)
        .extract()
        .as(TaskResponseDto[].class);

    assertThat(tasks).hasSize(1);
  }

}