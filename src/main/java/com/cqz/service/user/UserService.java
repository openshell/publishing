package com.cqz.service.user;

import com.cqz.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author openshell
 * @date 2019/4/16
 * @description
 */

public interface UserService {
    /**
     *  添加用户
     * @author openshell
     * @date 2019/4/16
     * @param [user]
     * @return int
     */
    int addUser(User user);



    /**
     * 
     * @author openshell
     * @date 2019/4/21
     * @param []
     * @return java.util.List<com.cqz.model.User>
     */
    List<User> findAllUsers();

    /**
     * 不分页查询
     * @author openshell
     * @date 2019/4/21
     * @param [pageNum, pageSize]
     * @return com.github.pagehelper.PageInfo<com.cqz.model.User>
     */
    PageInfo<User> findAllUser(int pageNum,int pageSize);

    /**
     * 根据用户名查找用户
     * @author openshell
     * @date 2019/4/17
     * @param [userName]
     * @return com.cqz.model.User
     */
    User getUserByName(String userName);

    /**
     * 检查用户名是否存在
     * @author openshell
     * @date 2019/4/19
     * @param [userName]
     * @return java.lang.Boolean
     */
    int checkName(String userName);

    void updateLoginTime(User user);
}
