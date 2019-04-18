package com.cqz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author openshell
 * @date 2019/4/17
 */
@Controller
@RequestMapping("/task")
public class TestController {


    @RequestMapping("home")
    public String goHome(Map<String ,Object> paraMap){
        paraMap.put("name","openshell");
        paraMap.put("age",24);
        return "home";
    }
    @RequestMapping("login")
    public String goLogin(){
        return "login";
    }


}
