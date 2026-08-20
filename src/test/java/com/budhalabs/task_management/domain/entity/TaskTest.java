package com.budhalabs.taskmanagement.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.budhalabs.taskmanagement.domain.exception.TaskAlreadyCompletedException;
import org.junit.jupiter.api.Test;

class TaskTest {

  @Test
  void shouldCreateTaskWithPendingStatus() {

    Task task = new Task("Learn Clean Architecture", "Build a Task Management API");

    assertEquals(TaskStatus.PENDING, task.getStatus());
  }

  @Test
  void shouldRejectBlankTitle() {

    assertThrows(IllegalArgumentException.class, () -> new Task("", "Some description"));
  }

  @Test
  void shouldCompleteTask() {

    Task task = new Task("Learn Clean Architecture", "Build a Task Management API");

    task.complete();

    assertEquals(TaskStatus.COMPLETED, task.getStatus());
  }

  @Test
  void shouldNotCompleteTaskTwice() {

    Task task = new Task("Learn Clean Architecture", "Build a Task Management API");

    task.complete();

    assertThrows(TaskAlreadyCompletedException.class, task::complete);
  }
}
