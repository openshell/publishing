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
                           @RequestParam(name = "pageSize", required = false, defaultValue = "5")
                                       int pageSize){

        return Msg.success().add("pageInfo",taskService.findAllTask(pageNum,pageSize));

    }

    @ResponseBody
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public Msg saveTask(Task task){
        System.out.println(task.toString());
        String selectPeop[] =task.getTaskSelectPeople().split(",");
        int[] userId = new int[selectPeop.length];
        for(int i=0;i<selectPeop.length;i++){
            userId[i] = Integer.parseInt(selectPeop[i]);
        }
        taskService.saveTask(userId,task);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value="/searchByName",method= RequestMethod.GET)
    public Msg searchKey( @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                      int pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5")
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
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5")
                                  int pageSize,
                          @RequestParam(name="userId",required = false)
                                  int userId
    ){

        return Msg.success().add("pageInfo",taskService.selectBackLog(userId,pageNum,pageSize));
    }

    @ResponseBody
    @RequestMapping(value="/updateTask", method=RequestMethod.GET)
    public Msg overTask(@RequestParam(name="taskId")int taskId,
                        @RequestParam(name="taskDescription")String taskDescription,
                        @RequestParam(name="userId")int userId
    ){

        return taskService.overTask(taskId,userId,taskDescription);

    }

    @ResponseBody
    @RequestMapping(value="/getById",method= RequestMethod.GET)
    public Msg getTaskById(@RequestParam(name="taskId",required = false) int taskId){

        return Msg.success().add("task",taskService.selectTaskById(taskId));
    }

    @ResponseBody
    @RequestMapping(value="/claim",method= RequestMethod.GET)
    public Msg claimTask(@RequestParam(name="taskId",required = false) int taskId,
        @RequestParam(name="userId",required = false) int userId,
        @RequestParam(name="taskType",required = false) String  taskType    ){

        return taskService.claimTask(taskId,userId,taskType);

    }
    @ResponseBody
    @RequestMapping(value="/getBacklogNum",method= RequestMethod.GET)
    public Msg getBacklogNum( @RequestParam(name="userId",required = false) int userId){

        return taskService.getBacklogNum(userId);

    }

    @ResponseBody
    @RequestMapping(value="/canClaim",method= RequestMethod.GET)
    public Msg getCanClaim(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                       int pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "5")
                                       int pageSize,
                           @RequestParam(name="userId",required = false)
                                       int userId){

        return taskService.getCanClaimTask(userId,pageNum,pageSize);

    }
    @ResponseBody
    @RequestMapping(value="/serachTaskByTime",method= RequestMethod.POST)
    public Msg getserachTaskByTime(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                       int pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "5")
                                       int pageSize,
                           @RequestParam(name="taskStartTime",required = false) String taskStartTime,
                                   @RequestParam(name="taskEndTime",required = false) String taskEndTime){

        return taskService.getserachTaskByTime(taskStartTime,taskEndTime,pageNum,pageSize);

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
