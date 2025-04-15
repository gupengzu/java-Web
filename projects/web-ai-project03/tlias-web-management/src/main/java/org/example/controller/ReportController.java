package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.StudentCountData;
import org.example.pojo.JobOption;
import org.example.pojo.Result;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("查询员工岗位数据");
        JobOption jobOption=reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("查询员工性别数据");
        List<Map<String,Object>> genderList=reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("查询学生数量数据");
        StudentCountData studentCountData=reportService.getStudentCountData();
        return Result.success(studentCountData);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("查询学生学位数据");
        return Result.success(reportService.getStudentDegreeData());
    }
}
