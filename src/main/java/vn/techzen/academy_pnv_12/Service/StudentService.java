package vn.techzen.academy_pnv_12.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techzen.academy_pnv_12.Model.Student;
import vn.techzen.academy_pnv_12.Repository.StudentRepository;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(int id, Student student) {
        studentRepository.update(id, student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.delete(id);
    }
}
