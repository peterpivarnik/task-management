package com.pp.js.tm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

/**
 * Abstract entity representing task.
 */
@MappedSuperclass
public abstract class Task extends BaseEntity {

  @Column(name = "created_at", nullable = false)
  @Convert(converter = Jsr310JpaConverters.InstantConverter.class)
  private Instant createdAt;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "task_management_user_id")
  private TaskManagementUser taskManagementUser;

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TaskManagementUser getTaskManagementUser() {
    return taskManagementUser;
  }

  public void setTaskManagementUser(TaskManagementUser taskManagementUser) {
    this.taskManagementUser = taskManagementUser;
  }

}
