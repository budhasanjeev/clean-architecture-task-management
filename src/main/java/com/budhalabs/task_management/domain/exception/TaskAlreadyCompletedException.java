package com.budhalabs.taskmanagement.domain.exception;

public class TaskAlreadyCompletedException extends RuntimeException {

  public TaskAlreadyCompletedException() {
    super("Task is already completed");
  }
}
