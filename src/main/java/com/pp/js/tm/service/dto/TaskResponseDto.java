package com.pp.js.tm.service.dto;

public class TaskResponseDto {

  private String name;
  private String uid;
  private String type;
  private UserResponseDto user;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public UserResponseDto getUser() {
    return user;
  }

  public void setUser(UserResponseDto user) {
    this.user = user;
  }
}
