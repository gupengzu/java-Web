package org.example.service;

import org.example.pojo.AllUsers;
import org.example.pojo.User;

public interface UserService {
    AllUsers query(String username, Integer awardCount, Integer page, Integer pageSize);

    void delete(Integer[] ids);

    void add(User user);
}
