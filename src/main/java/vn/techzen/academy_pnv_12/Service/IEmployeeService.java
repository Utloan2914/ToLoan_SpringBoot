package vn.techzen.academy_pnv_12.Service;

import vn.techzen.academy_pnv_12.Dto.EmployeeResponse;
import vn.techzen.academy_pnv_12.Model.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IEmployeeService {
    List<EmployeeResponse> getAllEmployees();
    Employee getEmployee(UUID id);

    void addEmployee(Employee employee);

    void updateEmployee(UUID id, Employee updatedEmployee);

    void deleteEmployee(UUID id);

    List<EmployeeResponse> getFilteredEmployees(
            String name,
            LocalDate dobFrom,
            LocalDate dobTo,
            String gender,
            String salaryRange,
            String phone,
            Integer departmentId
    );
}
