package com.pp.js.tm.service.dto;

import java.time.LocalDateTime;

public class CreateFeatureDto {

  private String name;
  private String businessValue;
  private LocalDateTime deadLine;

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
}
