package org.brytelabs.orm.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.brytelabs.orm.Employee;
import org.brytelabs.orm.core.domain.JoinCondition;
import org.brytelabs.orm.core.generators.GroupByGenerator;
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

    Constructor<?>[] constructors = Employee.class.getDeclaredConstructors();
    Constructor<?> constructor = constructors[0];
    Parameter[] parameters = constructor.getParameters();
    for (Parameter parameter : parameters) {
      //            parameter.
    }
  }

  @Test
  public void getClassParams() {
    Constructor<?>[] constructors = SuperClass.class.getDeclaredConstructors();
    Constructor<?>[] subConstructor = SubClass.class.getDeclaredConstructors();
    List<Field> superFields = ReflectionUtils.getDeclaredFields(SuperClass.class);
    List<Field> subFields = ReflectionUtils.getDeclaredFields(SubClass.class);
    Constructor<?>[] groupConstructors = GroupByGenerator.class.getDeclaredConstructors();
    assertEquals(2, constructors.length);
  }


  public static class SuperClass {
    private final String name;
    private final int age;

    public SuperClass(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public SuperClass(String name) {
      this.name = name;
      this.age = 10;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }
  }

  public static class SubClass extends SuperClass {
    private String otherField;

    public SubClass(String otherField, String name, int age) {
      super(name, age);
      this.otherField = otherField;
    }

    public String getOtherField() {
      return otherField;
    }
  }
}
