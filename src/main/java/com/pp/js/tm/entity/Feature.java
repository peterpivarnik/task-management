package com.pp.js.tm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

/**
 * Entity representing db table feature.
 */
@Entity
@Table(name = "feature")
public class Feature extends Task {

  @Column(name = "business_value")
  private String businessValue;

  @Column(name = "deadline")
  @Convert(converter = Jsr310JpaConverters.InstantConverter.class)
  private Instant deadline;

  public String getBusinessValue() {
    return businessValue;
  }

  public void setBusinessValue(String businessValue) {
    this.businessValue = businessValue;
  }

  public Instant getDeadline() {
    return deadline;
  }

  public void setDeadline(Instant deadline) {
    this.deadline = deadline;
  }
}
