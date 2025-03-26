package com.pp.js.tm.service.dto;

import java.time.LocalDateTime;

public class BugResponseDto {

  private String name;
  private String severity;
  private String steps;
  private LocalDateTime createdAt;
  private String uid;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }
}
