package com.cqz.service.user.impl;

import com.cqz.dao.UserMapper;
import com.cqz.model.User;
import com.cqz.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author openshell
 * @date 2019/4/16
 */

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;
    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users= userMapper.selectAllUser();
        PageInfo pageInfo=new PageInfo(users);
        return pageInfo;
    }

    @Override
    public User getUserByName(String userName) {

        return userMapper.selectByUserName(userName);
    }
}
