package org.brytelabs.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.brytelabs.orm.Employee.Gender;
import org.brytelabs.orm.Employee.MaritalStatus;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.Select;
import org.brytelabs.orm.api.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class QueryExecutorIntTest extends BaseIntTest {

  private static final RowMapper<Employee> employeeMapper =
      (row) ->
          new Employee(
              row.getLong("id"),
              row.getString("name"),
              row.getString("email"),
              row.getDate("birth_date").toLocalDate(),
              row.getBigDecimal("salary"),
              row.getBoolean("has_children"),
              Gender.valueOf(row.getString("gender")),
              MaritalStatus.valueOf(row.getString("marital_status")));

  QueryExecutor executor;

  @BeforeAll
  public void init() {
    executor = new SqlQueryExecutor(connection, true);
  }

  @Test
  public void selectAll() {
    Query query = Select.from("employee").build();
    List<Employee> employees = executor.findList(query, employeeMapper);
    assertEquals(employees.size(), 200);

    query = Select.from(Table.with("employee", "e")).where("gender").eq(Gender.MALE).build();

    List<Employee> maleEmployees = executor.findList(query, employeeMapper);
    assertEquals(maleEmployees, filter(employees, e -> Gender.MALE.equals(e.getGender())));

    query =
        Select.from(Table.with("employee", "e"))
            .where("gender")
            .eq(Gender.MALE)
            .and("marital_status")
            .eq(MaritalStatus.MARRIED)
            .build();

    List<Employee> marriedMaleEmployees = executor.findList(query, employeeMapper);
    assertEquals(
        marriedMaleEmployees,
        filter(
            employees,
            e ->
                Gender.MALE.equals(e.getGender())
                    && MaritalStatus.MARRIED.equals(e.getMaritalStatus())));

    query = Select.from(Table.with("employee", "e")).where("salary").between(1500, 1800).build();

    List<Employee> employeesWithSalaryBetween1500And1800 = executor.findList(query, employeeMapper);
    assertEquals(
        employeesWithSalaryBetween1500And1800,
        filter(
            employees,
            e -> e.getSalary().doubleValue() >= 1500 && e.getSalary().doubleValue() <= 1800));
  }

  @Test
  public void findWithReturnType() {
    Query query = Select.from("employee").where("id").in(1, 2).build();
    List<Employee> result = executor.findList(query, Employee.class);
    assertEquals(result.size(), 2);

    assertEquals(result.get(0).getId(), 1);
    assertEquals(result.get(0).getGender(), Gender.MALE);
    assertEquals(result.get(0).getMaritalStatus(), MaritalStatus.SINGLE);
    assertEquals(result.get(0).getName(), "Jared Moses");
    assertEquals(result.get(0).getSalary(), BigDecimal.valueOf(4601));
    assertEquals(result.get(0).getEmail(), "fermentum.metus.Aenean@porttitorvulputateposuere.net");
    assertEquals(result.get(0).getBirthDate(), LocalDate.parse("1981-12-03"));

    assertEquals(result.get(1).getId(), 2);
    assertEquals(result.get(1).getGender(), Gender.FEMALE);
    assertEquals(result.get(1).getMaritalStatus(), MaritalStatus.SINGLE);
    assertEquals(result.get(1).getName(), "Finn Arnold");
    assertEquals(result.get(1).getSalary(), BigDecimal.valueOf(1321));
    assertEquals(result.get(1).getEmail(), "faucibus.lectus.a@risusMorbimetus.com");
    assertEquals(result.get(1).getBirthDate(), LocalDate.parse("1947-03-15"));
  }

  @Test
  public void groupBy() {
    Query query =
        Select.from("employee", "gender", "count(*)")
            .where("birth_date")
            .gt(LocalDate.parse("1980-01-01"))
            .groupBy("gender")
            .build();

    List<KeyValue<String, Long>> results =
        executor.findList(
            query,
            rs -> {
              String gender = rs.getString(1);
              long count = rs.getLong(2);
              return new KeyValue<>(gender, count);
            });

    assertFalse(results.isEmpty());
    KeyValue<String, Long> male =
        results.stream().filter(r -> r.key.equals("MALE")).findFirst().orElse(null);
    KeyValue<String, Long> female =
        results.stream().filter(r -> r.key.equals("FEMALE")).findFirst().orElse(null);
    assertNotNull(male);
    assertNotNull(female);
    assertEquals(male.value, 39L);
    assertEquals(female.value, 53L);
  }

  @Test
  @Disabled
  public void selectDistinct() {
    Query query =
        Select.from("employee", "distinct gender")
            .where("birth_date")
            .gt(LocalDate.parse("1980-01-01"))
            .build();

    List<String> results = executor.findList(query, rs -> rs.getString(1));
    assertEquals(results.size(), 2);
  }

  private static List<Employee> filter(List<Employee> employees, Predicate<Employee> predicate) {
    return employees.stream().filter(predicate).collect(Collectors.toList());
  }

  public static class KeyValue<K, V> {
    private final K key;
    private final V value;

    public KeyValue(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
}
