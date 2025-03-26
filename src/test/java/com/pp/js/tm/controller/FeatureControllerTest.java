package com.pp.js.tm.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.pp.js.tm.service.dto.FeatureResponseDto;
import com.pp.js.tm.service.dto.CreateFeatureDto;
import com.pp.js.tm.testservice.TestFeatureService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class FeatureControllerTest {

  @LocalServerPort
  protected int port;

  @Autowired
  private TestFeatureService testFeatureService;

  @BeforeEach
  void beforeEach() {
    testFeatureService.deleteAll();
  }

  @Test
  void shouldCreateFeature() {
    CreateFeatureDto request = createFeatureDto();

    FeatureResponseDto bugResponseDto = given()
        .port(port)
        .accept(JSON)
        .contentType(JSON)
        .body(request)
        .when()
        .post("/task-management/feature")
        .then()
        .statusCode(200)
        .extract()
        .as(FeatureResponseDto.class);

    assertThat(bugResponseDto.getName()).isEqualTo(request.getName());
    assertThat(bugResponseDto.getBusinessValue()).isEqualTo(request.getBusinessValue());
    assertThat(bugResponseDto.getDeadLine()).isEqualTo(request.getDeadLine());
    assertThat(bugResponseDto.getUid()).isNotNull();
    assertThat(bugResponseDto.getCreatedAt()).isNotNull();
  }

  private CreateFeatureDto createFeatureDto() {
    CreateFeatureDto createFeatureDto = new CreateFeatureDto();
    createFeatureDto.setName("bugName");
    createFeatureDto.setBusinessValue("Business value");
    createFeatureDto.setDeadLine(LocalDateTime.now());
    return createFeatureDto;
  }
}