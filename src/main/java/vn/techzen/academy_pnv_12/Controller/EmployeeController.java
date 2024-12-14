package vn.techzen.academy_pnv_12.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_12.Exception.AppException;
import vn.techzen.academy_pnv_12.Exception.ErrorCode;
import vn.techzen.academy_pnv_12.Model.Employee;
import vn.techzen.academy_pnv_12.Response.JsonResponse;
import vn.techzen.academy_pnv_12.DTO.EmployeeSearchRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee(UUID.randomUUID(), "Hoàng Văn Hải", LocalDate.of(1990, 1, 15), "Nam", 15000000.00, "0975123542", "Nhân viên"),
            new Employee(UUID.randomUUID(), "Trần Thị Hoài", LocalDate.of(1985, 5, 20), "Nữ", 14500000.00, "0767869568", "Kế toán"),
            new Employee(UUID.randomUUID(), "Lê Văn Sỹ", LocalDate.of(1992, 3, 10), "Nam", 15500000.00, "0988881110", "Sale-Marketing"),
            new Employee(UUID.randomUUID(), "Phạm Duy Khánh", LocalDate.of(1988, 7, 5), "Nam", 14800000.00, "9986555333", "Sản xuất"),
            new Employee(UUID.randomUUID(), "Hoàng Văn Quý", LocalDate.of(1995, 9, 25), "Nam", 15200000.00, "0973388665", "Nhân viên")
    ));

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        logger.info("Fetching all employees");
        return JsonResponse.ok("Successfully fetched employees", employees);
    }

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        if (!employee.getPhone().matches("^0[0-9]{9}$")) {
            throw new AppException(ErrorCode.INVALID_PHONE, "Invalid phone number: " + employee.getPhone());
        }
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        logger.info("Added new employee: {}", employee.getName());
        return JsonResponse.created("Employee added successfully", employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable UUID id, @RequestBody Employee updatedEmployee) {
        if (!updatedEmployee.getPhone().matches("^0[0-9]{9}$")) {
            throw new AppException(ErrorCode.INVALID_PHONE, "Invalid phone number: " + updatedEmployee.getPhone());
        }

        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                employee.setName(updatedEmployee.getName());
                employee.setBirthDate(updatedEmployee.getBirthDate());
                employee.setGender(updatedEmployee.getGender());
                employee.setSalary(updatedEmployee.getSalary());
                employee.setPhone(updatedEmployee.getPhone());
                employee.setDepartment(updatedEmployee.getDepartment());
                logger.info("Updated employee: {}", employee.getName());
                return JsonResponse.ok("Employee updated successfully", employee);
            }
        }
        throw new AppException(ErrorCode.EMPLOYEE_NOT_FOUND, "Employee with id " + id + " not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable UUID id) {
        boolean removed = employees.removeIf(employee -> employee.getId().equals(id));
        if (removed) {
            logger.info("Deleted employee with id {}", id);
            return JsonResponse.noContent("Employee deleted successfully");
        } else {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_FOUND, "Employee with id " + id + " not found");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEmployees(@ModelAttribute EmployeeSearchRequest searchRequest) {
        List<Employee> filteredEmployees = new ArrayList<>(employees);

        if (searchRequest.getName() != null && !searchRequest.getName().isEmpty()) {
            filteredEmployees.removeIf(employee -> !employee.getName().toLowerCase().contains(searchRequest.getName().toLowerCase()));
        }
        if (searchRequest.getBirthDateFrom() != null && !searchRequest.getBirthDateFrom().isEmpty()) {
            LocalDate fromDate = LocalDate.parse(searchRequest.getBirthDateFrom());
            filteredEmployees.removeIf(employee -> employee.getBirthDate().isBefore(fromDate));
        }
        if (searchRequest.getBirthDateTo() != null && !searchRequest.getBirthDateTo().isEmpty()) {
            LocalDate toDate = LocalDate.parse(searchRequest.getBirthDateTo());
            filteredEmployees.removeIf(employee -> employee.getBirthDate().isAfter(toDate));
        }
        if (searchRequest.getGender() != null && !searchRequest.getGender().isEmpty()) {
            filteredEmployees.removeIf(employee -> !employee.getGender().equalsIgnoreCase(searchRequest.getGender()));
        }
        if (searchRequest.getSalary() != null) {
            filteredEmployees.removeIf(employee -> employee.getSalary() < searchRequest.getSalary());
        }
        if (searchRequest.getPhone() != null && !searchRequest.getPhone().isEmpty()) {
            filteredEmployees.removeIf(employee -> !employee.getPhone().contains(searchRequest.getPhone()));
        }
        if (searchRequest.getDepartment() != null && !searchRequest.getDepartment().isEmpty()) {
            filteredEmployees.removeIf(employee -> !employee.getDepartment().equalsIgnoreCase(searchRequest.getDepartment()));
        }

        return JsonResponse.ok("Successfully fetched filtered employees", filteredEmployees);
    }
}