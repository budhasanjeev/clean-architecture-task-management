package com.budhalabs.taskmanagement.domain.repository;

import com.budhalabs.taskmanagement.domain.entity.Task;

public interface TaskRepository {

  Task save(Task task);
}
