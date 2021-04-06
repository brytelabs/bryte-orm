package org.brytelabs.orm;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
  public enum Gender {
    MALE,
    FEMALE
  }

  public enum MaritalStatus {
    MARRIED,
    SINGLE,
    DIVORCED
  }

  private Long id;
  private String name;
  private String email;
  private LocalDate birthDate;
  private BigDecimal salary;
  private boolean hasChildren;
  private Gender gender;
  private MaritalStatus maritalStatus;
}
