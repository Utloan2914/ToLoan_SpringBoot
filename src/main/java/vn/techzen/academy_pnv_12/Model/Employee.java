package vn.techzen.academy_pnv_12.Model;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    private UUID id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private double salary;
    private String phone;
    private String department;
}
