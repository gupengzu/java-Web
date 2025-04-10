package org.example.service;

import org.example.pojo.JobOption;
import org.example.pojo.StudentCountData;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    StudentCountData getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
