package com.pp.js.tm.service.dto;

import java.time.Instant;

public class UpdateFeatureDto {

  private String uid;
  private String name;
  private Instant deadline;
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

  public Instant getDeadline() {
    return deadline;
  }

  public void setDeadline(Instant deadline) {
    this.deadline = deadline;
  }

  public String getBusinessValue() {
    return businessValue;
  }

  public void setBusinessValue(String businessValue) {
    this.businessValue = businessValue;
  }
}
