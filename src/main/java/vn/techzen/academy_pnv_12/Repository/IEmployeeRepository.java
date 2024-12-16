package vn.techzen.academy_pnv_12.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.techzen.academy_pnv_12.Dto.EmployeeResponse;
import vn.techzen.academy_pnv_12.Model.Employee;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IEmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query("SELECT new vn.techzen.academy_pnv_12.Dto.EmployeeResponse(e, d) " +
            "FROM Employee e " +
            "JOIN e.department d " +
            "WHERE (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:dobFrom IS NULL OR e.dob >= :dobFrom) " +
            "AND (:dobTo IS NULL OR e.dob <= :dobTo) " +
            "AND (:gender IS NULL OR e.gender = :gender) " +
            "AND (:phone IS NULL OR e.phone LIKE CONCAT('%', :phone, '%')) " +
            "AND (:departmentId IS NULL OR e.department.id = :departmentId) " +
            "AND (" +
            "  :salaryRange IS NULL OR " +
            "  (:salaryRange = 'lt5' AND e.salary < 5000000) OR " +
            "  (:salaryRange = '5-10' AND e.salary BETWEEN 5000000 AND 10000000) OR " +
            "  (:salaryRange = '10-20' AND e.salary BETWEEN 10000000 AND 20000000) OR " +
            "  (:salaryRange = 'gte20' AND e.salary >= 20000000)" +
            ")")
    List<EmployeeResponse> getFilteredEmployees(
            String name,
            LocalDate dobFrom,
            LocalDate dobTo,
            String gender,
            String salaryRange,
            String phone,
            Integer departmentId);

    @Query("SELECT new vn.techzen.academy_pnv_12.Dto.EmployeeResponse(e, d) " +
            "FROM Employee e " +
            "JOIN e.department d")
    List<EmployeeResponse> findAllWithDepartment();

}