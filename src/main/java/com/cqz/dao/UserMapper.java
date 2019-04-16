package com.cqz.dao;

import com.cqz.model.User;

import java.util.List;

public interface UserMapper {

    /**
     *
     * @author openshell
     * @date 2019/4/16
     * @param []
     * @return java.util.List<com.cqz.model.User>
     */
    List<User> selectAllUser();

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}