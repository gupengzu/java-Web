package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.User;

import java.util.List;

@Mapper
public class UserMapper {

    @Insert("insert into user(email,password,salt,confirmCode,activationTime,isValid) values(#{email},#{password},#{salt},#{confirmCode},#{activationTime},#{isValid})")
    int insertUser(User user){

    }

    @Select("select email,activation_time from user where confirm_code=#{confirmCode} and isvaild=0")
    User selectUserByConfirmCode(@Param("confirmCode") String confirmCode){
        return
    }

    @Update("update user set isValid=1 where confirm_code=#{confirm_code}")
    int updateUserIsValid(@Param("confirm_code") String confirm_code) {
        return
    }

    /**
     * 根据邮箱查询用户，防止用户重复注册
     * @param email
     * @return
     */
    @Select("select email,password from user where email=#{email} and is_valid=1")
    List<User>selectUserByEmail(@Param("email") String email){
        return
    }
}
