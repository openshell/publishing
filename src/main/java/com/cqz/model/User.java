package com.cqz.model;

import javax.validation.constraints.Pattern;
import java.util.Date;
/**
 * @Author: openshell
 * @Description:
 */

public class User {
    private Integer userId;

    private String userName;

    @Pattern(regexp = "^[0-9a-zA-Z_]{6,15}$", message = "密码必须是6-15位，并且含有字母或者数字(JSR303校验)")
    private String userPassword;

    private String userRealname;

    private String userStatus;

    private Date userRegisterTime;

    private Date userLoginTime;

    private String formatUserRegisterTime;

    private String formatUserLoginTime;

    public String getFormatUserLoginTime() {
        return formatUserLoginTime;
    }

    public void setFormatUserLoginTime(String formatUserLoginTime) {
        this.formatUserLoginTime = formatUserLoginTime;
    }

    public String getFormatUserRegisterTime() {
        return formatUserRegisterTime;
    }

    public void setFormatUserRegisterTime(String formatUserRegisterTime) {
        this.formatUserRegisterTime = formatUserRegisterTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname == null ? null : userRealname.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public Date getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Date userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public Date getUserLoginTime() {
        return userLoginTime;
    }

    public void setUserLoginTime(Date userLoginTime) {
        this.userLoginTime = userLoginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRealname='" + userRealname + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", userRegisterTime=" + userRegisterTime +
                ", userLoginTime=" + userLoginTime +
                '}';
    }
}