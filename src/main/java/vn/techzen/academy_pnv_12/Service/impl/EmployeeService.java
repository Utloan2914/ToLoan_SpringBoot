package vn.techzen.academy_pnv_12.Service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.techzen.academy_pnv_12.Dto.EmployeeResponse;
import vn.techzen.academy_pnv_12.Model.Employee;
import vn.techzen.academy_pnv_12.Repository.EmployeeRepository;
import vn.techzen.academy_pnv_12.Repository.IEmployeeRepository;
import vn.techzen.academy_pnv_12.Service.IEmployeeService;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeService implements IEmployeeService {
    @Autowired
    IEmployeeRepository employees;

    @Override
    public Page<EmployeeResponse> getAllEmployees(Pageable pageable) {
        return employees.findAllWithDepartment(pageable);
    }

    @Override
    public Employee getEmployee(UUID id) {
        return employees.findById(id).orElse(null);
    }

    @Override
    public void addEmployee(Employee employee) {
        employee.setId(UUID.randomUUID());
        employees.save(employee);
    }

    @Override
    public void updateEmployee(UUID id, Employee updatedEmployee) {
        if (employees.existsById(id)) {
            updatedEmployee.setId(id);
            employees.save(updatedEmployee);
        }
    }


    @Override
    public void deleteEmployee(UUID id) {
       employees.deleteById(id);
    }

    @Override
    public Page<EmployeeResponse> getFilteredEmployees(
            String name, LocalDate dobFrom, LocalDate dobTo, String gender,
            String salaryRange, String phone, Integer departmentId, Pageable pageable) {
        return employees.getFilteredEmployees(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId, pageable);
    }
}
