package vn.techzen.academy_pnv_12.Model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
public class Department {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    List<Employee> employees;

}
