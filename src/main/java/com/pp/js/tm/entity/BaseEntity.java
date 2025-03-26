package com.pp.js.tm.entity;

import static jakarta.persistence.GenerationType.SEQUENCE;

import com.pp.js.tm.entity.listener.UidBaseEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;

/**
 * Parent entity for all other entities.
 */
@MappedSuperclass
@EntityListeners(UidBaseEntityListener.class)
public abstract class BaseEntity {

  private static final String COL_ID_SEQ = "sq_id";

  @Id
  @Column(name = "id", nullable = false)
  @SequenceGenerator(name = COL_ID_SEQ, allocationSize = 1)
  @GeneratedValue(strategy = SEQUENCE, generator = COL_ID_SEQ)
  private Integer id;

  @Column(name = "uuid", nullable = false, unique = true)
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
