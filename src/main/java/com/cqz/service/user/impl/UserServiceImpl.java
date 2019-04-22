package com.cqz.service.user.impl;

import com.cqz.dao.UserMapper;
import com.cqz.model.User;
import com.cqz.service.user.UserService;
import com.cqz.utils.FormatDate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

        user.setUserRegisterTime(new Date());
        user.setUserLoginTime((new Date()));
        return userMapper.insertSelective(user);
    }

    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users= userMapper.selectAllUser();
        for (User user: users) {
            user.setFormatUserRegisterTime(FormatDate.getFormatDateWithTime(user.getUserRegisterTime()));
            user.setFormatUserLoginTime(FormatDate.getFormatDateWithTime(user.getUserLoginTime()));

        }

        PageInfo pageInfo=new PageInfo(users);
        return pageInfo;
    }

    /**
     *  新建 任务时，调用人员列表
     * @author openshell
     * @date 2019/4/22
     * @param []
     * @return java.util.List<com.cqz.model.User>
     */
    @Override
    public List<User> findAllUsers() {
        List<User> users= userMapper.selectAllUser();
        for (User user: users) {
            user.setFormatUserRegisterTime(FormatDate.getFormatDateWithTime(user.getUserRegisterTime()));
            user.setFormatUserLoginTime(FormatDate.getFormatDateWithTime(user.getUserLoginTime()));

        }
        return users;
    }


    @Override
    public User getUserByName(String userName) {
        User user=userMapper.selectByUserName(userName);
        user.setFormatUserRegisterTime(FormatDate.getFormatDateWithTime(user.getUserRegisterTime()));
        user.setFormatUserLoginTime(FormatDate.getFormatDateWithTime(user.getUserLoginTime()));
        return user;
    }

    @Override
    public int checkName(String userName) {

        return userMapper.countUserByName(userName);
    }

    @Override
    public void updateLoginTime(User user) {
        user.setUserLoginTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }
}
