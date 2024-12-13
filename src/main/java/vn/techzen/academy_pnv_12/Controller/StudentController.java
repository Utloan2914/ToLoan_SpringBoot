package vn.techzen.academy_pnv_12.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_12.Model.Student;
import vn.techzen.academy_pnv_12.Service.IStudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    // GET
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    // GET {id}
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    // POST
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return student;
    }

    // PUT
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return studentService.getStudentById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }
}
