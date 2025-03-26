package com.pp.js.tm.service.dto;

public class CreateBugDto {

  private String name;
  private String severity;
  private String steps;

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
}
