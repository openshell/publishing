package com.cqz.service.task.impl;

import com.cqz.dao.ReleMapper;
import com.cqz.dao.TaskMapper;
import com.cqz.model.Msg;
import com.cqz.model.Rele;
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

    private static String RELE_TYPE_SELF_SELECT="1";
    //任务被认领标志
    private static String RELE_TYPE_BE_CLAIM="1";
    private static String RELE_TYPE_BE_SELECT="3";
    private static String RELE_TYPE_BE_OVER="2";




    @Override
    public PageInfo<Task> findAllTask(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        System.out.println("-----"+pageNum);
        List<Task> tasks=taskMapper.selectAllTask();
        tasks=FormatDate.formatTask(tasks);
        PageInfo pageInfo=new PageInfo(tasks);
        return pageInfo;
    }

    @Override
    public void saveTask(int[] userId,Task task) {
        //为新建任务设置创建时间
        task.setTaskStartTime(new Date());

        //创建新的任务，并获取该任务的taskId
        taskMapper.insertSelective(task);

        Rele rele=new Rele();
        rele.setReleTaskId(task.getTaskId());
        for (int id:userId  ) {
            System.out.println("taskId is"+id);
            rele.setReleUserId(id);
            releMapper.insertSelective(rele);
        }



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
        List<Task> tasks= taskMapper.selectBackLog(userId,RELE_TYPE_BE_CLAIM);
        tasks=FormatDate.formatTask(tasks);

        PageInfo pageInfo=new PageInfo(tasks);
        return pageInfo;
    }

    @Override
    public Task selectTaskById(int id) {
        taskMapper.selectByPrimaryKey(id);
        return taskMapper.selectByPrimaryKey(id);
    }

    /**
     *
     * @author openshell
     * @date 2019/4/19
     * @param taskId, userId, taskType]
     * @return int 0为失败，1为成功
     */
    @Override
    public Msg claimTask(int taskId, int userId, String taskType) {
        Rele rele = new Rele();
        Task task = new Task();
        if (releMapper.selectIsBeSelect(taskId,userId)==1){
            System.out.println("该用户被该任务选中");
            if ("claim".equals(taskType)){
                System.out.println("该任务为认领类");
                if(releMapper.selectIsBeClaim(taskId,userId,RELE_TYPE_BE_CLAIM)==0){
                    System.out.println("该任务没有被认领");
                    if (releMapper.updateReleType(userId, taskId, RELE_TYPE_BE_CLAIM)==1){
                        return Msg.success();
                    }
                }
            }else if("coordination".equals(taskType)){
                //查询自己是否已经认领
                if(releMapper.selectIsBeClaim(taskId,userId,RELE_TYPE_BE_CLAIM)==0){
                    //更新该用户对应的任务为被认领（进行中）
                    if (releMapper.updateReleType(userId, taskId, RELE_TYPE_BE_CLAIM)==1){
                        return Msg.success();
                    }else{
                        return Msg.fail().add("errorInfo","任务认领失败！");
                    }
                }else {
                    return Msg.fail().add("errorInfo","任务认领失败！");
                }
            }
        }else{
            return Msg.fail().add("errorInfo","您不是被选中的用户！");
        }
        return Msg.fail().add("errorInfo","未知错误！");
    }
    @Override
    public void saveRele(int[] userId, Integer taskId) {
        Rele rele=new Rele();
        rele.setReleTaskId(taskId);
        for (int i:userId  ) {
            rele.setReleUserId(i);
            releMapper.insertSelective(rele);
        }
    }
}
