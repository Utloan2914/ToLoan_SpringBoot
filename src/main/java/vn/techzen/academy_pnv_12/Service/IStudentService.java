package vn.techzen.academy_pnv_12.Service;

import vn.techzen.academy_pnv_12.Model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudents();
    Student getStudentById(int id);
    void createStudent(Student student);
    void updateStudent(int id, Student student);
    void deleteStudent(int id);
}
