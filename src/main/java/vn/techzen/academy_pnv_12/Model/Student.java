package vn.techzen.academy_pnv_12.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private int id;
    private String name;
    private double age;

    public Student(int id, String name, double age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
