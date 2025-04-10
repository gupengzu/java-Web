package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.NumberAndStudents;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.Student_origin_in_SQL;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result queryStudents( @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "degree", required = false) Integer degree,
                                 @RequestParam(value = "clazzId", required = false) Integer clazzId,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){

        log.info("查询学生信息");
        NumberAndStudents students= studentService.queryStudents(name,degree,clazzId,page,pageSize);
        return Result.success(students);
    }

    @DeleteMapping("/{ids}")
    public Result deleteStudent(@PathVariable("ids") List<Integer> ids){
        log.info("删除学生信息");
        studentService.deleteStudent(ids);
        return Result.success();
    }

    @PostMapping
    public Result addStudent(@RequestBody Student student){
        log.info("添加学生信息");
        studentService.addStudent(student);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询学生信息");
        Student_origin_in_SQL student=studentService.getById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result updateStudent(@RequestBody Student student){
        log.info("更新学生信息");
        studentService.updateStudent(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{sorce}")
    public Result punishStudent(@PathVariable Integer id,@PathVariable Short sorce){
        log.info("更新学生信息");
        studentService.punishStudent(id,sorce);
        return Result.success();
    }
}
