package org.example.service;

import org.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询所有部门信息
    List<Dept> findAll();

    void deleteById(Integer id);

    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
