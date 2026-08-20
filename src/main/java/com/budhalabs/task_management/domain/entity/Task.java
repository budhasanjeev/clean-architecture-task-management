package com.budhalabs.taskmanagement.domain.entity;

import com.budhalabs.taskmanagement.domain.exception.TaskAlreadyCompletedException;
import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

  private UUID id;
  private String title;
  private String description;
  private TaskStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Task(String title, String description) {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Task title cannot be blank");
    }

    this.id = UUID.randomUUID();
    this.title = title;
    this.description = description;
    this.status = TaskStatus.PENDING;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = this.createdAt;
  }

  public void complete() {
    if (status == TaskStatus.COMPLETED) {
      throw new TaskAlreadyCompletedException();
    }

    status = TaskStatus.COMPLETED;
    updatedAt = LocalDateTime.now();
  }

  public TaskStatus getStatus() {
    return status;
  }

  public String getTitle() {
    return title;
  }
}
