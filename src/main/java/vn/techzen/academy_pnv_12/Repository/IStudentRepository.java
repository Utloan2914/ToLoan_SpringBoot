package vn.techzen.academy_pnv_12.Repository;

import vn.techzen.academy_pnv_12.Model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();
    Student findById(int id);
    void save(Student student);
    void update(int id, Student student);
    void delete(int id);
}
