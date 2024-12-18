package vn.techzen.academy_pnv_12.Service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.techzen.academy_pnv_12.Dto.employee.EmployeeResponse;
import vn.techzen.academy_pnv_12.Mapper.IEmployeeMapper;
import vn.techzen.academy_pnv_12.Model.Employee;
import vn.techzen.academy_pnv_12.Repository.IEmployeeRepository;
import vn.techzen.academy_pnv_12.Service.IEmployeeService;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public  class EmployeeService implements IEmployeeService {
    @Autowired
    IEmployeeRepository employeeRepository;

    @Autowired
    @Qualifier("")
    IEmployeeMapper employeeMapper;

    @Override
    public Page<EmployeeResponse> getAllEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(employee -> employeeMapper.employeeToEmployeeResponse(employee));
    }

    @Override
    public Page<EmployeeResponse> getFilteredEmployees(
            String name, LocalDate dobFrom, LocalDate dobTo, String gender,
            String salaryRange, String phone, Integer departmentId, Pageable pageable) {
        Page<Employee> employees = employeeRepository.getFilteredEmployees(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId, pageable);
        return employees.map(employee -> employeeMapper.employeeToEmployeeResponse(employee));
    }


    @Override
    public Employee getEmployee(UUID id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void addEmployee(Employee employee) {
        employee.setId(UUID.randomUUID());
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(UUID id, Employee updatedEmployee) {
        if (employeeRepository.existsById(id)) {
            updatedEmployee.setId(id);
            employeeRepository.save(updatedEmployee);
        }
    }
    @Override
    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }
    @Override
    public ResponseEntity<EmployeeResponse> findAll(Pageable pageable) {
        return null;
    }

}