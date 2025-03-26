package com.pp.js.tm.testservice;

import com.pp.js.tm.entity.Feature;
import com.pp.js.tm.repository.FeatureRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class TestFeatureService {

  private final FeatureRepository featureRepository;

  public TestFeatureService(FeatureRepository featureRepository) {
    this.featureRepository = featureRepository;
  }

  public void deleteAll() {
    featureRepository.deleteAll();
  }

  public Feature createFeature() {
    Feature feature = getFeature();
    return featureRepository.save(feature);
  }

  private Feature getFeature() {
    Feature feature = new Feature();
    feature.setType("feature");
    feature.setDeadline(Instant.now());
    feature.setBusinessValue("businessValue");
    feature.setName("name");
    feature.setCreatedAt(Instant.now());
    return feature;
  }
}
