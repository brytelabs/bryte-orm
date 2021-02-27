package org.brytelabs.orm;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class Employee {
    public enum Gender {
        MALE, FEMALE
    }

    public enum MaritalStatus {
        MARRIED, SINGLE, DIVORCED
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
