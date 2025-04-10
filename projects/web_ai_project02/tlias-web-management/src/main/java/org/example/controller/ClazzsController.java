package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Clazz;
import org.example.pojo.Clazz_origin_in_SQL;
import org.example.pojo.NumberAndClazzs;
import org.example.pojo.Result;
import org.example.service.ClazzsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzsController {
    @Autowired
    private ClazzsService clazzsService;

    //班级列表查询
    @GetMapping
    public Result queryClazzs(  @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "begin", required = false) String begin,
                                @RequestParam(value = "end", required = false) String end,
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        log.info("查询班级信息");
        NumberAndClazzs numberAndClazzs=clazzsService.queryClazzs(name,begin,end,page,pageSize);
        return Result.success(numberAndClazzs);
    }

    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable("id") Integer id){
        log.info("删除班级信息");
        clazzsService.deleteClazz(id);
        return Result.success();
    }

    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){
        log.info("添加班级信息");
        clazzsService.addClazz(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询班级信息");
        Clazz clazz=clazzsService.getById(id);
        Clazz_origin_in_SQL clazz_origin_in_sql=new Clazz_origin_in_SQL(clazz.getId(),clazz.getName(),clazz.getRoom(),clazz.getBeginDate(),clazz.getEndDate(),clazz.getMasterId(),clazz.getSubject(),clazz.getCreateTime(),clazz.getUpdateTime());
        return Result.success(clazz_origin_in_sql);
    }

    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz){
        log.info("修改班级信息");
        clazzsService.updateClazz(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result queryAllClazzs(){
        log.info("查询班级信息");
        List<Clazz_origin_in_SQL>clazzes=clazzsService.queryAllClazzs();
        return Result.success(clazzes);
    }
}
