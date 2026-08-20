package com.budhalabs.taskmanagement.application.usecase;

import com.budhalabs.taskmanagement.domain.entity.Task;
import com.budhalabs.taskmanagement.domain.repository.TaskRepository;

public class CreateTaskService implements CreateTaskUseCase {
  private final TaskRepository taskRepository;

  public CreateTaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public Task execute(String title, String description) {

    Task task = new Task(title, description);
    return taskRepository.save(task);
  }
}
