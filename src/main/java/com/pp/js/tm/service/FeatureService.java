package com.pp.js.tm.service;

import static java.time.ZoneOffset.UTC;

import com.pp.js.tm.entity.Feature;
import com.pp.js.tm.repository.FeatureRepository;
import com.pp.js.tm.service.dto.CreateFeatureDto;
import com.pp.js.tm.service.dto.FeatureResponseDto;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

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
}
