package vn.techzen.academy_pnv_12.Service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import vn.techzen.academy_pnv_12.Dto.employee.EmployeeResponse;
import vn.techzen.academy_pnv_12.Model.Employee;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.UUID;

public interface IEmployeeService {

    Page<EmployeeResponse> getAllEmployees(Pageable pageable);
    Page<EmployeeResponse> getFilteredEmployees(
            String name, LocalDate dobFrom, LocalDate dobTo, String gender,
            String salaryRange, String phone, Integer departmentId, Pageable pageable);

    Employee getEmployee(UUID id);
    void addEmployee(Employee employee);
    void updateEmployee(UUID id, Employee updatedEmployee);
    void deleteEmployee(UUID id);
    ResponseEntity<EmployeeResponse> findAll(Pageable pageable);
}