package com.cqz.controller;

import com.cqz.model.Msg;
import com.cqz.model.Task;
import com.cqz.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author openshell
 * @date 2019/4/18
 */
@Controller
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    TaskService taskService;
    @ResponseBody
    @RequestMapping(value = "/all")
    public Msg findAllTask(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                       int pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                       int pageSize){
        return Msg.success().add("pageInfo",taskService.findAllTask(pageNum,pageSize));

    }

    @ResponseBody
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public Msg saveTask(Task task){
        System.out.println(task.toString());

        if(taskService.saveTask(task)>0){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    @ResponseBody
    @RequestMapping(value="/searchByName",method= RequestMethod.GET)
    public Msg searchKey( @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                      int pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                      int pageSize,
                            @RequestParam(name="searchKey",required = false)
        String searchKey
    ){
        System.out.println(searchKey);

        return Msg.success().add("pageInfo",taskService.selectTaskByName(searchKey,pageNum,pageSize));
    }

    @ResponseBody
    @RequestMapping(value="/allBackLog",method= RequestMethod.GET)
    public Msg searchKey( @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                  int pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                  int pageSize,
                          @RequestParam(name="userId",required = false)
                                  int userId
    ){

        return Msg.success().add("pageInfo",taskService.selectBackLog(userId,pageNum,pageSize));
    }

    @ResponseBody
    @RequestMapping(value="/getById",method= RequestMethod.GET)
    public Msg getTaskById(@RequestParam(name="taskId",required = false) int taskId){

        return Msg.success().add("task",taskService.selectTaskById(taskId));
    }

    @ResponseBody
    @RequestMapping(value="/claim",method= RequestMethod.GET)
    public Msg claimTask(@RequestParam(name="taskId",required = false) int taskId,
        @RequestParam(name="userId",required = false) int userId
        ){

        if(taskService.claimTask(taskId,userId)>1){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    @RequestMapping(value = "/toAllTask")
    public String toAllTask(){
        return "/account/allTask";
    }
    @RequestMapping(value = "/toBackLog")
    public String toBackLog(){
        return "/account/backLog";
    }
}
