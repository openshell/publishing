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
     *
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
}
