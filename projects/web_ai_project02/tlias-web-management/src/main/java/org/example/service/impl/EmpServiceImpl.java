package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpLogService;
import org.example.service.EmpService;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        PageHelper.startPage(page,pageSize);
//        List<Emp> empList= empMapper.list(name,gender,begin,end);
//        Page<Emp> p=(Page<Emp>)empList;
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> empList= empMapper.list(empQueryParam);
        Page<Emp> p=(Page<Emp>)empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Transactional
    @Override
    public void save(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            List<EmpExpr>exprList=emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"新增员工："+emp);
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(Integer[] ids) {

        //1.删除员工基本信息
        empMapper.deleteByIds(ids);

        //2.删除员工对应的工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.updateById(emp);

        empExprMapper.deleteByEmpIds(new Integer[]{emp.getId()});

        List<EmpExpr>exprList=emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {

        Emp e=empMapper.selectByUsernameAndPassword(emp);

        if(e!=null){
            log.info("登录成功,员工信息"+e);
            Map<String, Object>claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt= JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }

        return null;
    }
}
