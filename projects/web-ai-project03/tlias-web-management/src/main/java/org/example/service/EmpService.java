package org.example.service;

import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.LoginInfo;
import org.example.pojo.PageResult;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    //新增员工信息
    void save(Emp emp);


    void delete(Integer[] ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
//    PageResult<Emp> page(Integer page, Integer pageSize,
//                         String name, Integer gender,
//                         LocalDate begin,LocalDate end);
    
}
