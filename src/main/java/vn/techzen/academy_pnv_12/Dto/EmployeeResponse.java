package vn.techzen.academy_pnv_12.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techzen.academy_pnv_12.Model.Department;
import vn.techzen.academy_pnv_12.Model.Employee;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {
    Employee employee;
    Department department;
}