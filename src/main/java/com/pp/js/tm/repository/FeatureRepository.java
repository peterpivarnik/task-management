package com.pp.js.tm.repository;

import com.pp.js.tm.entity.Feature;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@link Feature} entity.
 */
@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {

  Optional<Feature> findByUid(String featureUid);
}

