package vn.techzen.academy_pnv_12.Model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Department {
    private UUID id;
    private String name;
}