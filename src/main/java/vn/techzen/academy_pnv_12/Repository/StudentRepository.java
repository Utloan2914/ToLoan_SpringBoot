package vn.techzen.academy_pnv_12.Repository;

import org.springframework.stereotype.Repository;
import vn.techzen.academy_pnv_12.Model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {
    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Huynh Thi To Loan", 9.8),
            new Student(2, "Nguyen Thi Mai", 9.5)
    ));

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public Student findById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Student student) {
        students.add(student);
    }

    @Override
    public void update(int id, Student student) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(student.getName());
                s.setAge(student.getAge());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        students.removeIf(s -> s.getId() == id);
    }
}
