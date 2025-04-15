package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
//RestController包含1.Controller，用于解耦。2.ResponseBody，如果返回对象是对象或者集合，直接转为JSON格式返回
@RestController
public class DeptController {


    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    public Result list(){
        System.out.println("查询全部部门信息");
        log.info("查询全部部门信息");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping("/depts")
    public Result delete(@RequestParam("id") Integer id){
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        System.out.println("新增部门信息"+dept);
        log.info("新增部门信息"+dept);
        deptService.add(dept);
        return Result.success();
    }

    @Log
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id){
        System.out.println("查询部门信息"+id);
        log.info("查询部门信息"+id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门信息"+dept);
        log.info("修改部门信息"+dept);
        deptService.update(dept);
        return Result.success();
    }
}
