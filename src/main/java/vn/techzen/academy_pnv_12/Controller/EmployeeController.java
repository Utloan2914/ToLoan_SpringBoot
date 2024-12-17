package vn.techzen.academy_pnv_12.Controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_12.Dto.EmployeeResponse;
import vn.techzen.academy_pnv_12.Dto.page.PageResponse;
import vn.techzen.academy_pnv_12.Exception.AppException;
import vn.techzen.academy_pnv_12.Exception.ErrorCode;
import vn.techzen.academy_pnv_12.Response.ApiResponse;
import vn.techzen.academy_pnv_12.Service.IEmployeeService;
import vn.techzen.academy_pnv_12.Model.Employee;
import vn.techzen.academy_pnv_12.Response.JsonResponse;


import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees(Pageable pageable) {
        Page<EmployeeResponse> employees = employeeService.getAllEmployees(pageable);
        return JsonResponse.ok(new PageResponse<>(employees));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployee(@PathVariable UUID id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee != null) {
            return JsonResponse.ok(employee);
        } else {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return JsonResponse.created(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable UUID id, @RequestBody Employee updatedEmployee) {
        if (updatedEmployee == null || updatedEmployee.getName() == null || updatedEmployee.getDob() == null || updatedEmployee.getGender() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Employee existingEmployee = employeeService.getEmployee(id);
        if (existingEmployee != null) {
            employeeService.updateEmployee(id, updatedEmployee);
            return JsonResponse.ok(updatedEmployee);
        } else {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        Employee existingEmployee = employeeService.getEmployee(id);
        if (existingEmployee != null) {
            employeeService.deleteEmployee(id);
            return JsonResponse.noContent();
        } else {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dobFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(value = "dobTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "salaryRange", required = false) String salaryRange,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "departmentId", required = false) Integer departmentId,
            Pageable pageable) {
        if (name != null && name.isEmpty()) name = null;
        if (salaryRange != null && salaryRange.isEmpty()) salaryRange = null;
        if (phone != null && phone.isEmpty()) phone = null;
        if (departmentId != null && departmentId == -1) departmentId = null;
        Page <EmployeeResponse> filteredEmployees = employeeService.getFilteredEmployees(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId, pageable);
        return JsonResponse.ok(new PageResponse<>(filteredEmployees));
    }
}