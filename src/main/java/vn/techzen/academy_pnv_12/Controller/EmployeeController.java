package vn.techzen.academy_pnv_12.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_12.Model.Employee;

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
            new Employee(UUID.randomUUID(), "Hoàng Văn Hải", LocalDate.of(1990, 1, 15), "Nam", 15000000.00, "0975123542"),
            new Employee(UUID.randomUUID(), "Trần Thị Hoài", LocalDate.of(1985, 5, 20), "Nữ", 14500000.00, "3767869568"),
            new Employee(UUID.randomUUID(), "Lê Văn Sỹ", LocalDate.of(1992, 3, 10), "Nam", 15500000.00, "0988881110"),
            new Employee(UUID.randomUUID(), "Phạm Duy Khánh", LocalDate.of(1988, 7, 5), "Nam", 14800000.00, "9986555333"),
            new Employee(UUID.randomUUID(), "Hoàng Văn Quý", LocalDate.of(1995, 9, 25), "Nam", 15200000.00, "0973388665")
    ));

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logger.info("Fetching all employees");
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        if (!employee.getPhone().matches("^0[0-9]{9}$")) {
            return ResponseEntity.badRequest().body(null);
        }
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        logger.info("Added new employee: {}", employee.getName());
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable UUID id, @RequestBody Employee updatedEmployee) {
        if (!updatedEmployee.getPhone().matches("^0[0-9]{9}$")) {
            return ResponseEntity.badRequest().body(null);
        }

        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                employee.setName(updatedEmployee.getName());
                employee.setBirthDate(updatedEmployee.getBirthDate());
                employee.setGender(updatedEmployee.getGender());
                employee.setSalary(updatedEmployee.getSalary());
                employee.setPhone(updatedEmployee.getPhone());
                logger.info("Updated employee: {}", employee.getName());
                return ResponseEntity.ok(employee);
            }
        }
        logger.warn("Employee with id {} not found", id);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        boolean removed = employees.removeIf(employee -> employee.getId().equals(id));
        if (removed) {
            logger.info("Deleted employee with id {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Employee with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }
}
