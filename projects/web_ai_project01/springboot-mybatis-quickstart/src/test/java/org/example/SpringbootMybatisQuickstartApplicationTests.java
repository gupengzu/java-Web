package org.example;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest//springBoot单元测试的注解 - 当前测试类中的测试方法运行时，会启动springboot项目 - IOC容器
class SpringbootMybatisQuickstartApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll(){
        List<User> all = userMapper.findAll();
        all.forEach(System.out::println);
    }
    @Test
    public void testDeleteById(){
        userMapper.deleteById(5);
    }

    @Test
    public void testInsert(){
        User user = new User(null, "admin", "1234", "admin", "18");
        userMapper.insert(user);
    }

    @Test
    public void testUpdate(){
        User user = new User(1, "zhouyu", "1234", "admin", "18");
        userMapper.update(user);
    }
}
