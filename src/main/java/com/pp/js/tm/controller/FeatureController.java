package com.pp.js.tm.controller;

import com.pp.js.tm.service.FeatureService;
import com.pp.js.tm.service.dto.CreateFeatureDto;
import com.pp.js.tm.service.dto.FeatureResponseDto;
import com.pp.js.tm.service.dto.UpdateFeatureDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for http resource /task-management/feature
 */
@RestController
public class FeatureController {

  private final FeatureService featureService;

  public FeatureController(FeatureService featureService) {
    this.featureService = featureService;
  }

  /**
   * Creates feature.
   *
   * @param createFeatureDto request for feature creation
   * @return created feature
   */
  @PostMapping(value = "/task-management/feature")
  public ResponseEntity<FeatureResponseDto> createFeature(@RequestBody CreateFeatureDto createFeatureDto) {
    FeatureResponseDto feature = featureService.createFeature(createFeatureDto);
    return ResponseEntity.ok(feature);
  }

  /**
   * Returns feature by feature uid.
   *
   * @param featureUid uid of feature
   * @return found feature
   */
  @GetMapping(value = "/task-management/feature/{featureUid}")
  public ResponseEntity<FeatureResponseDto> getFeatureByUid(@PathVariable String featureUid) {
    FeatureResponseDto feature = featureService.getFeature(featureUid);
    return ResponseEntity.ok(feature);
  }

  /**
   * Updates feature with new attributes.
   *
   * @param updateFeatureDto request holding update info
   * @return updated feature
   */
  @PutMapping(value = "/task-management/feature")
  public ResponseEntity<FeatureResponseDto> updateFeature(@RequestBody UpdateFeatureDto updateFeatureDto) {
    FeatureResponseDto feature = featureService.updateFeature(updateFeatureDto);
    return ResponseEntity.ok(feature);
  }
}
