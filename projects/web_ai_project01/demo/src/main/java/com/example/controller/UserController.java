package com.example.controller;

import cn.hutool.core.io.IoUtil;
import com.example.service.UserService;
import com.example.service.UserServiceImpL;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//用户信息的Controller
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/list")
    public List<User> list() throws FileNotFoundException {

        List<User> userList = userService.findAll();

        return userList;
    }
}
