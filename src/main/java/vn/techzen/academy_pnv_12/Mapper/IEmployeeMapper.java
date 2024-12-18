package vn.techzen.academy_pnv_12.Mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import vn.techzen.academy_pnv_12.Dto.employee.EmployeeRequest;
import vn.techzen.academy_pnv_12.Dto.employee.EmployeeResponse;
import vn.techzen.academy_pnv_12.Model.Employee;
@Mapper(componentModel = "spring")
@Component
public interface IEmployeeMapper {
    Employee employeeRequestToEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse employeeToEmployeeResponse(Employee employee);
}