package org.brytelabs.orm.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.brytelabs.orm.Employee;
import org.junit.jupiter.api.Test;

class ReflectionUtilsTest {

  @Test
  public void getDeclaredFields() {
    List<Field> fields = ReflectionUtils.getDeclaredFields(Employee.class);
    assertEquals(fields.size(), 8);
  }

  @Test
  public void getDeclaredFieldsFromClassWithSuperClass() {
    List<Field> fields = ReflectionUtils.getDeclaredFields(SubClass.class);
    assertEquals(fields.size(), 3);
    assertEquals(
        fields.stream().map(Field::getName).collect(Collectors.toList()),
        Arrays.asList("otherField", "name", "age"));
  }

  @Test
  public void createInstanceFromAllArgsConstructor() {
    Employee emp =
        new Employee(
            1L,
            "",
            "",
            LocalDate.MAX,
            BigDecimal.ONE,
            true,
            Employee.Gender.FEMALE,
            Employee.MaritalStatus.DIVORCED);
    Constructor<?> constructor = Employee.class.getDeclaredConstructors()[0];
    Parameter[] parameters = constructor.getParameters();
    for (Parameter parameter : parameters) {
      //            parameter.
    }
  }

  //    @Test
  //    public void getClassParams() {
  //        ResultSetToTypeMapper<Employee> mapper = new ResultSetToTypeMapper<>();
  //        Class<?> type = ReflectionUtils.getTypeParam(mapper.getClass());
  //        assertEquals(type, Employee.class);
  //    }

  public static class SuperClass {
    private String name;
    private int age;
  }

  public static class SubClass extends SuperClass {
    private String otherField;
  }
}
