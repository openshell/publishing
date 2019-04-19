package com.cqz.dao;

import com.cqz.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User
 * @Author: openshell
 * @Description:
 */
@Repository
public interface UserMapper {

    /**
     * 获得密码
     * @author openshell
     * @date 2019/4/17
     * @param username
     * @return java.lang.String
     */
    String getPassword(String username);

    /**
     * 获得用户启用状态
     * @author openshell
     * @date 2019/4/17
     * @param username
     * @return java.lang.String
     */
    String getRole(String username);
    /**
     * 查询全部用户
     * @author openshell
     * @date 2019/4/16
     * @param
     * @return java.util.List<com.cqz.model.User>
     */
    List<User> selectAllUser();

    /**
     * 根据主键删除用户
     * @author openshell
     * @date 2019/4/16
     * @param userId
     * @return int
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 插入用户记录
     * @author openshell
     * @date 2019/4/16
     * @param record
     * @return int
     */
    int insert(User record);

    /**
     * 只会插入数据不为null的字段值
     * @author openshell
     * @date 2019/4/16
     * @param record
     * @return int
     */
    int insertSelective(User record);

    /**
     * 根据主键查询
     * @author openshell
     * @date 2019/4/16
     * @param userId
     * @return com.cqz.model.User
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 根据主键更新，只会更新数据不为null的字段值
     * @author openshell
     * @date 2019/4/16
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键更新
     * @author openshell
     * @date 2019/4/16
     * @param record
     * @return int
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查询用户
     * @author openshell
     * @date 2019/4/17
     * @param userName
     * @return com.cqz.model.User
     */
    User selectByUserName(String userName);


    /**
     *
     * @author openshell
     * @date 2019/4/19
     * @param [userName]
     * @return int
     */
    int countUserByName(String userName);
}