package vn.techzen.academy_pnv_12.Dto.employee;

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
    String name;
    LocalDate dob;
    String gender;
    String phone;
    Double salary;
    Integer departmentId;
}