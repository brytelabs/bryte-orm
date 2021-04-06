package org.brytelabs.orm.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.brytelabs.orm.Employee.Gender;
import org.brytelabs.orm.Result;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ResultTest {

  @Test
  public void sqlValueResult() throws SQLException {

    Instant instant = Instant.now();
    Timestamp timestamp = Timestamp.from(instant);

    ResultSet resultSet = Mockito.mock(ResultSet.class);
    Mockito.when(resultSet.getInt("int")).thenReturn(1);
    Mockito.when(resultSet.getString("string")).thenReturn("MALE");
    Mockito.when(resultSet.getTimestamp("timestamp")).thenReturn(timestamp);
    Mockito.when(resultSet.getObject("obj")).thenReturn(timestamp);
    Mockito.when(resultSet.getDate("date")).thenReturn(Date.valueOf("2020-01-31"));
    Result result = new Result(resultSet);

    assertEquals(result.getInt("int"), 1);
    assertEquals(result.getString("string"), "MALE");
    assertEquals(result.getEnum("string", Gender.class), Gender.MALE);
    assertEquals(result.getLocalDate("date"), LocalDate.parse("2020-01-31"));
    assertEquals(result.getLocalDateTime("timestamp"), timestamp.toLocalDateTime());
    assertEquals(result.getInstant("obj"), instant);
    assertEquals(result.getZonedDateTime("obj"), instant.atZone(ZoneOffset.UTC));
  }
}
