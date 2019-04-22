package com.cqz.service.task;

import com.cqz.model.Msg;
import com.cqz.model.Task;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * @author openshell
 * @date 2019/4/18
 * @description
 */
@Service
public interface TaskService {
    /**
     * 查询所有任务
     * @author openshell
     * @date 2019/4/18
     * @param []
     * @return com.cqz.model.Task
     */
    PageInfo<Task> findAllTask(int pageNum, int pageSize);


    /**
     * 保存任务
     * @author openshell
     * @date 2019/4/18
     * @param task
     * @return int
     */
    void saveTask(int[] userId,Task task);

    /**
     * 根据任务名查询
     * @author openshell
     * @date 2019/4/18
     * @param searchKey
     * @return com.github.pagehelper.PageInfo<com.cqz.model.Task>
     */
    PageInfo<Task> selectTaskByName(String searchKey, int pageNum, int pageSize);

    /**
     * 分页查询待办任务任务
     * @author openshell
     * @date 2019/4/18
     * @param [userId, pageNum, pageSize]
     * @return com.github.pagehelper.PageInfo<com.cqz.model.Task>
     */
    PageInfo<Task> selectBackLog(int userId, int pageNum, int pageSize);

    Task selectTaskById(int id);

    Msg claimTask(int taskId, int userId, String taskType);

    /**
     * 保存任务关联
     * @author openshell
     * @date 2019/4/19
     * @param [userId, taskId]
     * @return int
     */
    void saveRele(int[] userId, Integer taskId);

    /**
     * 办结任务，更新task表和关联表
     * @author openshell
     * @date 2019/4/20
     * @param taskId userId taskDescription
     * @return com.cqz.model.Msg
     */
    Msg overTask(int taskId,int userId, String taskDescription);

    /**
     *
     * @author openshell
     * @date 2019/4/19
     * @param userId
     * @return com.cqz.model.Msg
     */
    Msg getBacklogNum(int userId);

    /**
     * 获取可以领的任务
     * @author openshell
     * @date 2019/4/19
     * @param [userId, pageNum, pageSize]
     * @return com.cqz.model.Msg
     */
    Msg getCanClaimTask(int userId, int pageNum, int pageSize);

    /**
     * 根据日期查询任务
     * @author openshell
     * @date 2019/4/19
     * @param [startTime, endTime, pageNum, pageSize]
     * @return com.cqz.model.Msg
     */
    Msg getserachTaskByTime(String taskStartTime, String taskEndTime, int pageNum, int pageSize);
}
