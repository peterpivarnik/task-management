package com.pp.js.tm.testservice;

import com.pp.js.tm.repository.FeatureRepository;
import jakarta.transaction.Transactional;
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
}
