package vn.techzen.academy_pnv_12.Model;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private UUID id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private double salary;
    private String phone;

    // Constructor
    public Employee(UUID id, String name, LocalDate birthDate, String gender, double salary, String phone) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.salary = salary;
        this.phone = phone;
    }
}