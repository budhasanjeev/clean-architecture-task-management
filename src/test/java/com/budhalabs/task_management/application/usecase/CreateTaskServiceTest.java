package com.budhalabs.taskmanagement.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.budhalabs.taskmanagement.domain.entity.Task;
import com.budhalabs.taskmanagement.domain.entity.TaskStatus;
import org.junit.jupiter.api.Test;

class CreateTaskServiceTest {

  @Test
  void shouldCreateTask() {
    FakeTaskRepository repository = new FakeTaskRepository();

    CreateTaskUseCase useCase = new CreateTaskService(repository);

    Task task = useCase.execute("Learn Clean Architecture", "Build a Task Management API");

    assertEquals("Learn Clean Architecture", task.getTitle());

    assertEquals(TaskStatus.PENDING, task.getStatus());

    assertEquals(task, repository.getSavedTask());
  }
}
