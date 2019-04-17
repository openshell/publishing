package com.cqz.shiro;

import com.cqz.dao.UserMapper;
import com.cqz.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author openshell
 * @date 2019/4/17
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    private final UserMapper userMapper;

    @Autowired
    public CustomRealm(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    /**获取身份验证信息
     *
     * @author openshell
     * @date 2019/4/17
     * @param token
     * @return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName=(String)token.getPrincipal();

        User user=userMapper.selectByUserName(userName);
        System.out.println(user);
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(userName,user.getUserPassword(),getName());

        return info;
    }

    /**
     * 获取状态信息
     * @author openshell
     * @date 2019/4/17
     * @param [principals]
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String)principals.getPrimaryPrincipal();
        User user = userMapper.selectByUserName(userName);
        String userStatus = user.getUserStatus();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermission(userStatus);
        return info;
    }
}

