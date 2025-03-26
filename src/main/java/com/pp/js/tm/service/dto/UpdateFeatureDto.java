package com.pp.js.tm.service.dto;

import java.time.LocalDateTime;

public class UpdateFeatureDto {

  private String uid;
  private String name;
  private LocalDateTime deadline;
  private String businessValue;

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDateTime deadline) {
    this.deadline = deadline;
  }

  public String getBusinessValue() {
    return businessValue;
  }

  public void setBusinessValue(String businessValue) {
    this.businessValue = businessValue;
  }
}
