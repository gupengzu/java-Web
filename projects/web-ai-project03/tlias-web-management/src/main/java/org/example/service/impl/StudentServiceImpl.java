package org.example.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.StudentMapper;
import org.example.pojo.NumberAndStudents;
import org.example.pojo.Student;
import org.example.pojo.Student_origin_in_SQL;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public NumberAndStudents queryStudents(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Student> students=studentMapper.queryStudents(name,degree,clazzId);
        Page<Student>p=(Page<Student>)students;
        NumberAndStudents numberAndStudents=new NumberAndStudents(p.getTotal(),p.getResult());
        return numberAndStudents;
    }

    @Override
    public void deleteStudent(List<Integer> ids) {
        studentMapper.deleteStudent(ids);
    }

    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount((short)0);
        student.setViolationScore((short)0);
        studentMapper.addStudent(student.getName(),student.getNo(),student.getGender(),student.getPhone(),student.getIdCard(),student.getIsCollege(),student.getAddress(),student.getDegree(),student.getGraduationDate(),student.getClazzId(),student.getViolationCount(),student.getViolationScore(),student.getCreateTime(),student.getUpdateTime());
    }

    @Override
    public Student_origin_in_SQL getById(Integer id) {
        Student_origin_in_SQL studentOriginInSql=studentMapper.getById(id);
        return studentOriginInSql;
    }

    @Override
    public void updateStudent(Student student) {
            student.setUpdateTime(LocalDateTime.now());
            studentMapper.updateStudent(student);
    }

    @Override
    public void punishStudent(Integer id, Short sorce) {
        studentMapper.punishStudent(id,sorce);
    }
}
