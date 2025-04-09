package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.User;

import java.util.List;

@Mapper//应用程序在运行时，会自动的为该接口创建一个实现类对象（代理对象），并且会自动将该实现类对象存入到IOC容器-bean
public interface UserMapper {
    //@Select("select * from user")
    public List<User> findAll();

    @Delete("delete from user where id= #{id}")
    public void deleteById(Integer id);

    @Insert("insert into user(username,password,name,age) values (#{username},#{password},#{name},#{age})")
    public void insert(User user);

    @Update("update user set username=#{username},password=#{password},name=#{name},age=#{age} where id=#{id}")
    public void update(User user);
}
