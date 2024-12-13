package vn.techzen.academy_pnv_12.Controller;

import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_12.Model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Huynh Thi To Loan", 9.8),
            new Student(2, "Nguyen Thi Mai", 8.5)
    ));

    // GET
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    // GET {id}
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // POST
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    // PUT
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(student.getName());
                s.setAge(student.getAge());
                return s;
            }
        }
        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        students.removeIf(s -> s.getId() == id);
    }
}