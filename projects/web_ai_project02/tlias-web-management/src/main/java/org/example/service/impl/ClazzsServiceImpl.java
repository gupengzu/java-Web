package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClazzsMapper;
import org.example.pojo.Clazz;
import org.example.pojo.Clazz_origin_in_SQL;
import org.example.pojo.NumberAndClazzs;
import org.example.service.ClazzsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@Service
public class ClazzsServiceImpl implements ClazzsService {
    @Autowired
    private ClazzsMapper clazzsMapper;

    //按页条件查询
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public NumberAndClazzs queryClazzs(String name, String begin, String end, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Clazz>clazzes=clazzsMapper.queryClazzs(name,begin,end);
        clazzes.forEach(clazz -> {
            //更新最新时间
            clazz.setUpdateTime(LocalDateTime.now());
            //判断是已结课，未开班，在读中
            //获取当前时间，并与开始时间比较，如果开始时间大于当前时间，则状态为未开班，否则为在读中
            if (clazz.getEndDate().isBefore(ChronoLocalDate.from(LocalDateTime.now()))){
                clazz.setStatus("已结课");
            }else if (clazz.getBeginDate().isAfter(ChronoLocalDate.from(LocalDateTime.now()))){
                clazz.setStatus("未开班");
            }else {
                clazz.setStatus("在读中");
            }
        });
        Page<Clazz>p=(Page<Clazz>)clazzes;
        NumberAndClazzs numberAndClazzs=new NumberAndClazzs(p.getTotal(),p.getResult());
        return numberAndClazzs;
    }


    //删除班级
    @Override
    public void deleteClazz(Integer id) {
        clazzsMapper.deleteClazz(id);
    }

    //添加班级
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazz.setCreateTime(LocalDateTime.now());

        clazzsMapper.addClazz(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        Clazz clazz=clazzsMapper.getById(id);
        return clazz;
    }

    //修改班级
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        deleteClazz(clazz.getId());
        clazzsMapper.updateClazz(clazz);
    }

    //查询全部班级的信息
    @Override
    public List<Clazz_origin_in_SQL> queryAllClazzs() {
        List<Clazz_origin_in_SQL> clazzes=clazzsMapper.queryAllClazzs();
        return clazzes;
    }
}
