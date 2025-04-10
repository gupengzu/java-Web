package org.example.service.impl;

import org.example.mapper.EmpMapper;
import org.example.pojo.JobOption;
import org.example.pojo.StudentCountData;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        List<Object> pos = list.stream().map(dateMap -> dateMap.get("pos")).toList();
        List<Object> data = list.stream().map(dateMap -> dateMap.get("num")).toList();
        return new JobOption(pos,data);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public StudentCountData getStudentCountData() {
        List<Map<String, Object>>list=empMapper.getStudentCountData();
        List<Object>name=list.stream().map(dateMap -> dateMap.get("name")).toList();
        List<Object>number=list.stream().map(dateMap -> dateMap.get("number")).toList();
        StudentCountData studentCountData = new StudentCountData(name,number);
        return studentCountData;
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return empMapper.getStudentDegreeData();
    }
}
