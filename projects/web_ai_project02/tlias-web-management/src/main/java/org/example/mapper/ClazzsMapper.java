package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Clazz;
import org.example.pojo.Clazz_origin_in_SQL;

import java.util.List;

@Mapper
public interface ClazzsMapper {
    List<Clazz> queryClazzs(String name, String begin, String end);

    @Delete("delete from clazz where id=#{id}")
    void deleteClazz(Integer id);

    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) values (#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void addClazz(Clazz clazz);

    @Select("select *from clazz where id=#{id};")
    Clazz getById(Integer id);

    @Insert("insert into clazz(id,name, room, begin_date, end_date, master_id, subject, create_time, update_time) values (#{id},#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void updateClazz(Clazz clazz);


    List<Clazz_origin_in_SQL> queryAllClazzs();
}
