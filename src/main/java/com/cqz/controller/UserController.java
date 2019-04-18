package com.cqz.controller;

import com.cqz.model.Msg;
import com.cqz.model.User;
import com.cqz.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author openshell
 * @date 2019/4/16
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(User user){
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Msg findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return Msg.success().add("pageInfo",userService.findAllUser(pageNum,pageSize));
    }



    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String getLogin(@RequestParam("userName")String userName, @RequestParam("userPassword")String userPassword, HttpServletRequest request) {

        Subject subject= SecurityUtils.getSubject();
        System.out.println("用户名和密码为："+userName+"and"+userPassword);
        UsernamePasswordToken token=new UsernamePasswordToken(userName,userPassword);
        try {
            subject.login(token);
            User user=userService.getUserByName(userName);
            request.getSession().setAttribute("loginUser", user);
            System.out.println("验证账户是否为启用："+subject.isPermitted("enable"));
            if(subject.isPermitted("enable")) {
                request.getSession().setAttribute("loginUser",user);
                return "/account/backLog";
            }
            return "/index";

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("用户密码错误");
            request.setAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping(value="/allUser")
    public String toAllUser(){
        return "/account/allUser.html";
    }

    @RequestMapping(value={"toLogin","logout"})
    public String toLogin(){
        return "login";
    }

}
