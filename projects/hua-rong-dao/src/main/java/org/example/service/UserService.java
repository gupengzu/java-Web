package org.example.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import org.example.pojo.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;


@Service
public class UserService {

    public Map<String,Object> createAccount(User user){
        //雪花算法生成确认码
        String confirmCode= IdUtil.getSnowflake(1,1).nextIdStr();
        //生成盐
        String salt= RandomUtil.randomString(6);
        //加密密码：原始密码+盐
        String md5Pwd= SecureUtil.md5(user.getPassword()+salt);
        //激活失效时间：24h
        user.setActivationTime(LocalDateTime.now().plusHours(24));
    }
}
