package com.pp.js.tm.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.pp.js.tm.entity.Bug;
import com.pp.js.tm.service.dto.BugResponseDto;
import com.pp.js.tm.service.dto.CreateBugDto;
import com.pp.js.tm.service.dto.UpdateBugDto;
import com.pp.js.tm.testservice.TestBugService;
import io.restassured.http.ContentType;
import java.util.List;
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


  @Test
  void shouldGetBugByUid() {
    Bug request = testBugService.createBug();

    BugResponseDto foundBug = given()
        .port(port)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .when()
        .pathParam("bugUid", request.getUid())
        .get("/task-management/bug/{bugUid}")
        .then()
        .statusCode(200)
        .extract()
        .as(BugResponseDto.class);

    assertThat(foundBug.getName()).isEqualTo(request.getName());
    assertThat(foundBug.getSeverity()).isEqualTo(request.getSeverity());
    assertThat(foundBug.getSteps()).isEqualTo(request.getSteps());
    assertThat(foundBug.getUid()).isNotNull();
    assertThat(foundBug.getCreatedAt()).isNotNull();
  }

  @Test
  void shouldUpdateBug() {
    Bug bug = testBugService.createBug();
    UpdateBugDto request = createUpdateBugDto(bug.getUid());

    BugResponseDto foundBug = given()
        .port(port)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .put("/task-management/bug")
        .then()
        .statusCode(200)
        .extract()
        .as(BugResponseDto.class);

    assertThat(foundBug.getName()).isEqualTo(request.getName());
    assertThat(foundBug.getSeverity()).isEqualTo(request.getSeverity());
    assertThat(foundBug.getSteps()).isEqualTo(request.getSteps());
    assertThat(foundBug.getUid()).isNotNull();
    assertThat(foundBug.getCreatedAt()).isNotNull();
  }

  private UpdateBugDto createUpdateBugDto(String uid) {
    UpdateBugDto updateBugDto = new UpdateBugDto();
    updateBugDto.setUid(uid);
    updateBugDto.setSeverity("ERROR");
    updateBugDto.setName("newName");
    updateBugDto.setSteps("new Steps");
    return updateBugDto;
  }

  @Test
  void shouldDeleteBug() {
    Bug bug = testBugService.createBug();

    List<Bug> allBugs = testBugService.findAll();
    assertThat(allBugs).hasSize(1);

    given()
        .port(port)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .when()
        .pathParam("bugUid", bug.getUid())
        .delete("/task-management/bug/{bugUid}")
        .then()
        .statusCode(204);

    allBugs = testBugService.findAll();
    assertThat(allBugs).isEmpty();
  }
}