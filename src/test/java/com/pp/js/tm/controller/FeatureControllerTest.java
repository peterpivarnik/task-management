package com.pp.js.tm.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.pp.js.tm.entity.Feature;
import com.pp.js.tm.service.dto.CreateFeatureDto;
import com.pp.js.tm.service.dto.FeatureResponseDto;
import com.pp.js.tm.service.dto.UpdateFeatureDto;
import com.pp.js.tm.testservice.TestFeatureService;
import io.restassured.http.ContentType;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
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

    FeatureResponseDto feature = given()
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

    assertThat(feature.getName()).isEqualTo(request.getName());
    assertThat(feature.getBusinessValue()).isEqualTo(request.getBusinessValue());
    assertThat(feature.getDeadLine()).isEqualTo(request.getDeadLine());
    assertThat(feature.getUid()).isNotNull();
    assertThat(feature.getCreatedAt()).isNotNull();
  }

  private CreateFeatureDto createFeatureDto() {
    CreateFeatureDto createFeatureDto = new CreateFeatureDto();
    createFeatureDto.setName("bugName");
    createFeatureDto.setBusinessValue("Business value");
    createFeatureDto.setDeadLine(LocalDateTime.now());
    return createFeatureDto;
  }

  @Test
  void shouldGetFeatureByUid() {
    Feature feature = testFeatureService.createFeature();

    FeatureResponseDto foundFeature = given()
        .port(port)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .when()
        .pathParam("featureUid", feature.getUid())
        .get("/task-management/feature/{featureUid}")
        .then()
        .statusCode(200)
        .extract()
        .as(FeatureResponseDto.class);

    assertThat(feature.getName()).isEqualTo(foundFeature.getName());
    assertThat(feature.getBusinessValue()).isEqualTo(foundFeature.getBusinessValue());
    assertThat(feature.getDeadline()).isNotNull();
    assertThat(feature.getUid()).isEqualTo(foundFeature.getUid());
    assertThat(feature.getCreatedAt()).isNotNull();
  }

  @Test
  void shouldUpdateFeature() {
    Feature feature = testFeatureService.createFeature();
    UpdateFeatureDto request = createUpdateFeatureDto(feature.getUid());

    FeatureResponseDto foundFeature = given()
        .port(port)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .put("/task-management/feature")
        .then()
        .statusCode(200)
        .extract()
        .as(FeatureResponseDto.class);

    assertThat(foundFeature.getUid()).isEqualTo(request.getUid());
    assertThat(foundFeature.getName()).isEqualTo(request.getName());
    assertThat(foundFeature.getBusinessValue()).isEqualTo(request.getBusinessValue());
    assertThat(foundFeature.getDeadLine()).isAfter(LocalDateTime.now());
  }

  private UpdateFeatureDto createUpdateFeatureDto(String uid) {
    UpdateFeatureDto updateFeatureDto = new UpdateFeatureDto();
    updateFeatureDto.setUid(uid);
    updateFeatureDto.setBusinessValue("new Business Value");
    updateFeatureDto.setName("newName");
    updateFeatureDto.setDeadline(Instant.now().plus(Duration.ofHours(50)));
    return updateFeatureDto;
  }

  @Test
  void shouldDeleteFeature() {
    Feature feature = testFeatureService.createFeature();

    List<Feature> allFeatures = testFeatureService.findAll();
    assertThat(allFeatures).hasSize(1);

    given()
        .port(port)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .when()
        .pathParam("featureUid", feature.getUid())
        .delete("/task-management/feature/{featureUid}")
        .then()
        .statusCode(204);

    allFeatures = testFeatureService.findAll();
    assertThat(allFeatures).isEmpty();
  }
}