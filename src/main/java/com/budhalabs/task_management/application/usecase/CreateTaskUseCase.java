package com.budhalabs.taskmanagement.application.usecase;

import com.budhalabs.taskmanagement.domain.entity.Task;

public interface CreateTaskUseCase {

  Task execute(String title, String description);
}
