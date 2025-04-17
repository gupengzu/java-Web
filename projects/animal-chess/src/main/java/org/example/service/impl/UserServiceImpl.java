package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.mapper.UserMapper;
import org.example.pojo.AllUsers;
import org.example.pojo.User;
import org.example.pojo.UserDTO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public AllUsers query(String username, Integer awardCount, Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        List<UserDTO> userDTOList = userMapper.query(username, awardCount);

        PageInfo<UserDTO>pageInfo=new PageInfo<>(userDTOList);

        long total=pageInfo.getTotal();

        return new AllUsers(total, userDTOList);
    }

    @Override
    public void delete(Integer[] ids) {
        userMapper.delete(ids);
    }

    @Override
    public void add(User user) {
        userMapper.add(user.getUsername(),user.getPassword(),user.getAward_count(),user.getImage());
    }
}
