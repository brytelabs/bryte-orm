package org.brytelabs.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseIntTest {
  Connection connection;

  @BeforeAll
  public void setupConnection() throws SQLException {
    connection =
        DriverManager.getConnection("jdbc:postgresql://localhost:15432/test", "test", "test1234");
  }

  @AfterAll
  public void cleanup() throws SQLException {
    connection.close();
  }
}
