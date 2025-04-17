package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.UserDTO;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserDTO> query(String username, Integer awardCount);

    void delete(Integer[] ids);

    @Insert("insert into user(username,password,award_count,image) values(#{username},#{password},#{award_count},#{image})")
    void add(String username, String password, Integer award_count, String image);
}
