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
import org.springframework.transaction.annotation.Transactional;

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

    private static String TASK_TYPE_CLAIM="claim";
    private static String TASK_TYPE_CORRDINATION="coordination";




    @Override
    public PageInfo<Task> findAllTask(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

System.out.println("sdfsdkfd");
        List<Task> tasks=taskMapper.selectAllTask();

        tasks=FormatDate.formatTask(tasks);

        //查询任务状态
        //首先判断任务类型
        //如果是认领制，直接读取t_rele表中的状态存入task对象中
        //如果是协同类需要统计t_rele表中 符合该条件的数量：t_rele_task_id =#{taskId} and ( t_rele_type='1' or t_rele_type='3')
        //当且仅当数量为0时为完结任务

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
                if(releMapper.selectIsBeClaim(taskId,RELE_TYPE_BE_CLAIM)==0){
                    System.out.println("该任务没有被认领");
                    if (releMapper.updateReleType(userId, taskId, RELE_TYPE_BE_CLAIM)==1){
                        return Msg.success();
                    }
                }else {
                    return Msg.fail().add("errorInfo","有人已经领取了该任务！");
                }
            }else if("coordination".equals(taskType)){
                //查询自己是否已经认领
                if(releMapper.selectIsBeSelectBySelf(taskId,userId,RELE_TYPE_BE_CLAIM)==0){
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

    @Override
    @Transactional(rollbackFor = IllegalArgumentException.class)
    public Msg overTask(int taskId,int userId, String taskDescription) {

        //首先应该判断任务类型
        Task task=new Task();

        task=taskMapper.selectByPrimaryKey(taskId);
        task.setTaskDescription(taskDescription);

        if(TASK_TYPE_CLAIM.equals(task.getTaskType())){
            //如果是认领类则直接更新任务状态
            task.setTaskStatus(RELE_TYPE_BE_OVER);
            if((taskMapper.updateByPrimaryKeySelective(task)==1) && (releMapper.updateReleType(userId,taskId,RELE_TYPE_BE_OVER)==1)){
                return Msg.success();
            }else {
                throw new RuntimeException("发生异常");
            }

        }else if(TASK_TYPE_CORRDINATION.equals(task.getTaskType())){

            //先更新t_rele表状态为2（over），并更新task_desc
            if((taskMapper.updateByPrimaryKeySelective(task)==1) && (releMapper.updateReleType(userId,taskId,RELE_TYPE_BE_OVER)==1)){
                ///此时判断t_rele表中该任务的完成数量，当且仅当数量为0时更新整个任务状态为t.task_status=2
                if(releMapper.selectCoorTaskOverCount(taskId,RELE_TYPE_BE_CLAIM,RELE_TYPE_BE_SELECT,TASK_TYPE_CORRDINATION)==0){

                    task.setTaskStatus(RELE_TYPE_BE_OVER);
                    taskMapper.updateByPrimaryKeySelective(task);
                    return Msg.success().add("errorInfo","任务完全办结");
                }

                return Msg.success().add("errorInfo","您的任务状态已更新为完成，请等待其他用户完成任务！");
            }else {
                throw new RuntimeException("发生异常，您提交的数据未生效！");
            }

        }else {
            throw new RuntimeException("发生异常，您提交的数据未生效！");
        }

    }

    /**
     * 获取可领任务的数量
     * @author openshell
     * @date 2019/4/22
     * @param userId
     * @return com.cqz.model.Msg
     */
    @Override
    public Msg getBacklogNum(int userId) {
       int backlogNum= releMapper.selectTaskAvailable(userId,RELE_TYPE_BE_SELECT,TASK_TYPE_CORRDINATION,TASK_TYPE_CLAIM,RELE_TYPE_BE_CLAIM);
       System.out.println("----------------------------"+backlogNum);
       return Msg.success().add("backlogNum",backlogNum);
    }

    /**
     *
     * @author openshell
     * @date 2019/4/19
     * @param [userId, pageNum, pageSize]
     * @return com.cqz.model.Msg
     */
    @Override
    public Msg getCanClaimTask(int userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<Task> tasks =   taskMapper.selectTaskAvailable(userId,RELE_TYPE_BE_SELECT,TASK_TYPE_CORRDINATION,TASK_TYPE_CLAIM,RELE_TYPE_BE_CLAIM);
        tasks=FormatDate.formatTask(tasks);

        PageInfo pageInfo=new PageInfo(tasks);

        return Msg.success().add("pageInfo",pageInfo);
    }

    @Override
    public Msg getserachTaskByTime(String taskStartTime, String taskEndTime, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Task> tasks =taskMapper.selectTaskByTime(taskStartTime,taskEndTime);
        tasks=FormatDate.formatTask(tasks);

        PageInfo pageInfo=new PageInfo(tasks);
        return Msg.success().add("pageInfo",pageInfo);
    }

}
