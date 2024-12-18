package vn.techzen.academy_pnv_12.Dto.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeSearchRequest {
    private String name;
    private String birthDateFrom;
    private String birthDateTo;
    private String gender;
    private Double salary;
    private String phone;
    private  Integer departmentId;
}