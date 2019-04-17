package com.cqz.service.user;

import com.cqz.model.User;
import com.github.pagehelper.PageInfo;

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
     * 查询所有用户
     * @author openshell
     * @date 2019/4/16
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
}
