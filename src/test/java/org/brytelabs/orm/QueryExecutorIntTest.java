package org.brytelabs.orm;

import org.brytelabs.orm.Employee.Gender;
import org.brytelabs.orm.Employee.MaritalStatus;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.Select;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.RowMapper;
import org.brytelabs.orm.exceptions.DataAccessException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class QueryExecutorIntTest extends BaseIntTest {
    private static final Logger log = Logger.getLogger(QueryExecutorIntTest.class.getName());

    private static final RowMapper<Employee> employeeMapper = (row) -> Employee.builder()
            .id(row.getLong("id"))
            .gender(Gender.valueOf(row.getString("gender")))
            .maritalStatus(MaritalStatus.valueOf(row.getString("marital_status")))
            .birthDate(row.getLocalDate("birth_date"))
            .hasChildren(row.getBoolean("has_children"))
            .email(row.getString("email"))
            .name(row.getString("name"))
            .salary(row.getBigDecimal("salary"))
            .build();

    @Test
    public void selectAll() {
        QueryExecutor executor = new SqlQueryExecutor(connection, true);

        Query query = Select.from("employee").build();
        List<Employee> employees = executor.findList(query, employeeMapper);
        assertEquals(employees.size(), 200);

        query = Select.from(Table.with("employee", "e"))
                .where("gender").eq(Gender.MALE)
                .build();

        List<Employee> maleEmployees = executor.findList(query, employeeMapper);
        assertEquals(maleEmployees, filter(employees, e -> Gender.MALE.equals(e.getGender())));

        query = Select.from(Table.with("employee", "e"))
                .where("gender").eq(Gender.MALE)
                .and("marital_status").eq(MaritalStatus.MARRIED)
                .build();

        List<Employee> marriedMaleEmployees = executor.findList(query, employeeMapper);
        assertEquals(marriedMaleEmployees, filter(employees,
                e -> Gender.MALE.equals(e.getGender()) && MaritalStatus.MARRIED.equals(e.getMaritalStatus())));

        query = Select.from(Table.with("employee", "e"))
                .where("salary").between(1500, 1800)
                .build();

        List<Employee> employeesWithSalaryBetween1500And1800 = executor.findList(query, employeeMapper);
        assertEquals(employeesWithSalaryBetween1500And1800, filter(employees,
                e -> e.getSalary().doubleValue() >= 1500 && e.getSalary().doubleValue() <= 1800));
    }

    private static List<Employee> filter(List<Employee> employees, Predicate<Employee> predicate) {
        return employees.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
