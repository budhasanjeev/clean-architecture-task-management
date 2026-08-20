package com.budhalabs.task_management;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;

@Testcontainers
@SpringBootTest
class TaskManagementApplicationTests {

  @Container @ServiceConnection
  static MySQLContainer mysql =
      new MySQLContainer("mysql:8.4")
          .withDatabaseName("task_management")
          .withUsername("task_management")
          .withPassword("task_management");

  @Test
  void contextLoads() {}
}
