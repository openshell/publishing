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
     * 
     * @author openshell
     * @date 2019/4/16
     * @param [pageNum, pageSize]
     * @return com.github.pagehelper.PageInfo<com.cqz.model.User>
     */
    PageInfo<User> findAllUser(int pageNum,int pageSize);
    
}
