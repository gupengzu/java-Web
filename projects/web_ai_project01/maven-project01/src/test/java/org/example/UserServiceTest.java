package org.example;

import com.example.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    public void testGetAge(){
        UserService userService=new UserService();
        Integer age = userService.getAge("100000200010011011");
        System.out.println(age);
    }

    @Test
    public void testGetGender(){
        UserService userService=new UserService();
        String gender = userService.getGender("100000200010011011");
        System.out.println(gender);
    }

    @Test
    public void testGenderWithAssert(){
        UserService userService=new UserService();
        String gender = userService.getGender("100000200010011011");
        Assertions.assertEquals("男",gender);
    }
}
