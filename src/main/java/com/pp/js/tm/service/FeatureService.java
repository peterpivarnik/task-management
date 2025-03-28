package com.pp.js.tm.service;

import com.pp.js.tm.entity.Feature;
import com.pp.js.tm.exception.EntityNotFoundException;
import com.pp.js.tm.repository.FeatureRepository;
import com.pp.js.tm.service.dto.CreateFeatureDto;
import com.pp.js.tm.service.dto.FeatureResponseDto;
import com.pp.js.tm.service.dto.UpdateFeatureDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

/**
 * Service providing extended functionality for {@link FeatureRepository}.
 */
@Service
@Transactional
public class FeatureService {

  private final FeatureRepository featureRepository;

  public FeatureService(FeatureRepository featureRepository) {
    this.featureRepository = featureRepository;
  }

  /**
   * Creates feature.
   *
   * @param createFeatureDto request containing info about feature creation
   * @return Created feature.
   */
  public FeatureResponseDto createFeature(CreateFeatureDto createFeatureDto) {
    Feature feature = getFeature(createFeatureDto);
    Feature savedFeature = featureRepository.save(feature);
    return mapToFeatureResponseDto(savedFeature);
  }

  private Feature getFeature(CreateFeatureDto createFeatureDto) {
    Feature feature = new Feature();
    feature.setDeadline(createFeatureDto.getDeadLine().toInstant(UTC));
    feature.setBusinessValue(createFeatureDto.getBusinessValue());
    feature.setName(createFeatureDto.getName());
    feature.setCreatedAt(Instant.now());
    feature.setType("feature");
    return feature;
  }

  private FeatureResponseDto mapToFeatureResponseDto(Feature savedFeature) {
    FeatureResponseDto response = new FeatureResponseDto();
    response.setName(savedFeature.getName());
    response.setBusinessValue(savedFeature.getBusinessValue());
    response.setCreatedAt(LocalDateTime.ofInstant(savedFeature.getCreatedAt(), UTC));
    response.setDeadLine(LocalDateTime.ofInstant(savedFeature.getDeadline(), UTC));
    response.setUid(savedFeature.getUid());
    return response;
  }

  /**
   * Returns feature by its uid.
   *
   * @param featureUid uid of feature
   * @return feature
   */
  public FeatureResponseDto getFeature(String featureUid) {
    return featureRepository.findByUid(featureUid)
                            .map(this::toFeatureResponse)
                            .orElseThrow(
                                () -> new EntityNotFoundException("Feature with uid" + featureUid + " not found!"));
  }

  private FeatureResponseDto toFeatureResponse(Feature feature) {
    FeatureResponseDto featureResponseDto = new FeatureResponseDto();
    featureResponseDto.setUid(feature.getUid());
    featureResponseDto.setDeadLine(feature.getDeadline() != null ? LocalDateTime.ofInstant(feature.getDeadline(), UTC) : null);
    featureResponseDto.setBusinessValue(feature.getBusinessValue());
    featureResponseDto.setName(feature.getName());
    featureResponseDto.setCreatedAt(LocalDateTime.ofInstant(feature.getCreatedAt(), UTC));
    return featureResponseDto;
  }

  public FeatureResponseDto updateFeature(UpdateFeatureDto updateFeatureDto) {
    return featureRepository.findByUid(updateFeatureDto.getUid())
                            .map(feature -> updateFeatureEntity(feature, updateFeatureDto))
                            .map(this::toFeatureResponse)
                            .orElseThrow(() -> new EntityNotFoundException(
                                "User with uid " + updateFeatureDto.getUid() + " not found!"));
  }

  private Feature updateFeatureEntity(Feature feature, UpdateFeatureDto updateFeatureDto) {
    feature.setName(updateFeatureDto.getName());
    feature.setDeadline(updateFeatureDto.getDeadline() != null ? updateFeatureDto.getDeadline().toInstant(UTC) : null);
    feature.setBusinessValue(updateFeatureDto.getBusinessValue());
    return featureRepository.save(feature);
  }

  public void deleteFeature(String featureUid) {
    featureRepository.findByUid(featureUid)
                     .ifPresent(featureRepository::delete);
  }
}
