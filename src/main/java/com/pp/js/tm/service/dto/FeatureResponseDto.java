package com.pp.js.tm.service.dto;

import java.time.LocalDateTime;

public class FeatureResponseDto {
  private String name;
  private String businessValue;
  private LocalDateTime deadLine;
  private LocalDateTime createdAt;
  private String uid;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBusinessValue() {
    return businessValue;
  }

  public void setBusinessValue(String businessValue) {
    this.businessValue = businessValue;
  }

  public LocalDateTime getDeadLine() {
    return deadLine;
  }

  public void setDeadLine(LocalDateTime deadLine) {
    this.deadLine = deadLine;
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
