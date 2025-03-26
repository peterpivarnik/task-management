package com.pp.js.tm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

/**
 * Abstract entity representing task.
 */
@Entity
@Table(name = "task")
@Inheritance(strategy= InheritanceType.JOINED)
public class Task extends BaseEntity {

  @Column(name = "created_at", nullable = false)
  @Convert(converter = Jsr310JpaConverters.InstantConverter.class)
  private Instant createdAt;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "task_management_user_id")
  private TaskManagementUser taskManagementUser;

  @Column(name = "type", nullable = false)
  private String type;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
