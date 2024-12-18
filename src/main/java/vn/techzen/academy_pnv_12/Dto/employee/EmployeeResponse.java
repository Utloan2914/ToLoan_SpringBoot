package vn.techzen.academy_pnv_12.Dto.employee;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techzen.academy_pnv_12.Model.Department;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    UUID id;
    String name;
    LocalDate dob;
    String gender;
    String phone;
    Double salary;
    Department department;
}
