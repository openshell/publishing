package com.cqz.service.task.impl;

import com.cqz.dao.ReleMapper;
import com.cqz.dao.TaskMapper;
import com.cqz.model.Task;
import com.cqz.service.task.TaskService;
import com.cqz.utils.FormatDate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author openshell
 * @date 2019/4/18
 */
@Service(value = "taskService")
public class TaskServiceImpl implements TaskService {
    @Autowired(required = false)
    TaskMapper taskMapper;
    @Autowired(required = false)
    ReleMapper releMapper;

    @Override
    public PageInfo<Task> findAllTask(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Task> tasks=taskMapper.selectAllTask();
        tasks=FormatDate.formatTask(tasks);
        PageInfo pageInfo=new PageInfo(tasks);
        return pageInfo;
    }

    @Override
    public int saveTask(Task task) {
        task.setTaskStartTime(new Date());
        return taskMapper.insertSelective(task);
    }


    @Override
    public PageInfo<Task> selectTaskByName(String searchKey, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Task> tasks= taskMapper.selectTaskByName(searchKey);
        tasks=FormatDate.formatTask(tasks);

        PageInfo pageInfo=new PageInfo(tasks);
        return pageInfo;

    }

    @Override
    public PageInfo<Task> selectBackLog(int userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Task> tasks= taskMapper.selectBackLog(userId);
        tasks=FormatDate.formatTask(tasks);

        PageInfo pageInfo=new PageInfo(tasks);
        return pageInfo;
    }

    @Override
    public Task selectTaskById(int id) {
        taskMapper.selectByPrimaryKey(id);
        return taskMapper.selectByPrimaryKey(id);
    }

    @Override
    public int claimTask(int taskId,int userId) {
        Task task=new Task();
        task.setTaskId(taskId);
        task.setTaskStatus("2");
        taskMapper.updateByPrimaryKeySelective(task);
        releMapper.updateRele(userId,taskId);
        return 0;
    }
}
