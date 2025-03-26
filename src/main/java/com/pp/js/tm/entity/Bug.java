package com.pp.js.tm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Entity representing db table bug.
 */
@Entity
@Table(name = "bug")
public class Bug extends Task {

  @Column(name = "severity")
  private String severity;

  @Column(name = "steps")
  private String steps;

  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public String getSteps() {
    return steps;
  }

  public void setSteps(String steps) {
    this.steps = steps;
  }
}
