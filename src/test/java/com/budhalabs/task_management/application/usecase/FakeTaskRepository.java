package com.budhalabs.taskmanagement.application.usecase;

import com.budhalabs.taskmanagement.domain.entity.Task;
import com.budhalabs.taskmanagement.domain.repository.TaskRepository;

class FakeTaskRepository implements TaskRepository {

  private Task savedTask;

  @Override
  public Task save(Task task) {
    this.savedTask = task;
    return task;
  }

  public Task getSavedTask() {
    return savedTask;
  }
}
