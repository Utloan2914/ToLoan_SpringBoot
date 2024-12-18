package vn.techzen.academy_pnv_12.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.techzen.academy_pnv_12.Dto.employee.EmployeeResponse;
import vn.techzen.academy_pnv_12.Model.Employee;

import java.time.LocalDate;
import java.util.UUID;

public interface IEmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("SELECT e FROM Employee e WHERE " +
            "( :name IS NULL OR e.name LIKE %:name% ) AND " +
            "( :dobFrom IS NULL OR e.dob >= :dobFrom ) AND " +
            "( :dobTo IS NULL OR e.dob <= :dobTo ) AND " +
            "( :gender IS NULL OR e.gender = :gender ) AND " +
            "( :salaryRange IS NULL OR e.salary = :salaryRange ) AND " +
            "( :phone IS NULL OR e.phone LIKE %:phone% ) AND " +
            "( :departmentId IS NULL OR e.department.id = :departmentId )")
    Page<Employee> getFilteredEmployees(
            String name,
            LocalDate dobFrom,
            LocalDate dobTo,
            String gender,
            String salaryRange,
            String phone,
            Integer departmentId,
            Pageable pageable);



}
