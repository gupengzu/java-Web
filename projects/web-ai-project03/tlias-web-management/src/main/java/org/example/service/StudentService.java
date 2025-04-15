package org.example.service;

import org.example.pojo.NumberAndStudents;
import org.example.pojo.Student;
import org.example.pojo.Student_origin_in_SQL;

import java.util.List;

public interface StudentService {
    NumberAndStudents queryStudents(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    void deleteStudent(List<Integer> ids);

    void addStudent(Student student);

    Student_origin_in_SQL getById(Integer id);

    void updateStudent(Student student);

    void punishStudent(Integer id, Short sorce);
}
