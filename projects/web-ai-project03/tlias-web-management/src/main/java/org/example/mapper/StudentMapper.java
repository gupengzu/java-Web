package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.Student;
import org.example.pojo.Student_origin_in_SQL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> queryStudents(String name, Integer degree, Integer clazz_id);

    void deleteStudent(List<Integer> ids);

    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time) " +
            "values (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{violationCount}, #{violation_score}, #{createTime}, #{updateTime})")
    void addStudent(String name, String no, Integer gender, String phone, String idCard, Integer isCollege, String address, Integer degree, LocalDate graduationDate, Integer clazzId, Short violationCount, Short violation_score, LocalDateTime createTime, LocalDateTime updateTime);

    @Select("select * from student where id=#{id}")
    Student_origin_in_SQL getById(Integer id);


    void updateStudent(Student student);

    @Update("update student set violation_score=violation_score+#{score},violation_count=violation_count+1 where id=#{id}")
    void punishStudent(Integer id, Short score);
}
