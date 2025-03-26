package com.pp.js.tm.entity;

import static jakarta.persistence.GenerationType.SEQUENCE;

import com.pp.js.tm.entity.listener.UidBaseEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Parent entity for all other entities.
 */
@MappedSuperclass
@EntityListeners(UidBaseEntityListener.class)
public abstract class BaseEntity {

  private static final String SEQUENCE_ID = "sq_id";

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_ID)
  private Integer id;

  @Column(name = "uid")
  private String uid;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }
}
