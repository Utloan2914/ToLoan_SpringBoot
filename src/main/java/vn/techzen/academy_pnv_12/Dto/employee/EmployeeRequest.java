package vn.techzen.academy_pnv_12.Dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequest {
    @NotBlank(message = "Name is mandatory")
    String name;

    @NotNull(message = "Date of birth is mandatory")
    LocalDate dob;

    @NotBlank(message = "Gender is mandatory")
    String gender;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,}$", message = "Phone number is not valid")
    String phone;

    @NotNull(message = "Salary is mandatory")
    Double salary;

    Integer departmentId;
}