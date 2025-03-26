package com.pp.js.tm.entity.listener;

import com.pp.js.tm.entity.BaseEntity;
import jakarta.persistence.PrePersist;
import java.util.UUID;

/**
 * Listener for {@link BaseEntity}.
 */
public class UidBaseEntityListener {

  /**
   * Set uid for every newly created entity.
   *
   * @param baseEntity parent entity
   */
  @PrePersist
  public void setUid(BaseEntity baseEntity) {
    if (baseEntity.getUid() == null) {
      baseEntity.setUid(UUID.randomUUID().toString());
    }
  }
}
