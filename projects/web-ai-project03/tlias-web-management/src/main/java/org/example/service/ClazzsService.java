package org.example.service;

import org.example.pojo.Clazz;
import org.example.pojo.Clazz_origin_in_SQL;
import org.example.pojo.NumberAndClazzs;

import java.util.List;

public interface ClazzsService {
    NumberAndClazzs queryClazzs(String name, String begin, String end, Integer page, Integer pageSize);

    void deleteClazz(Integer id);

    void addClazz(Clazz clazz);

    Clazz getById(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz_origin_in_SQL> queryAllClazzs();
}
