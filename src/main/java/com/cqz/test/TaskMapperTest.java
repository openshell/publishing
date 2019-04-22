package com.cqz.test;

import com.cqz.PublishingApplication;
import com.cqz.dao.ReleMapper;
import com.cqz.dao.TaskMapper;
import com.cqz.model.Task;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author openshell
 * @date 2019/4/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PublishingApplication.class)
public class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    ReleMapper releMapper;
    @Ignore
    public void selectTest(){
        List<Task> tasks=taskMapper.selectAllTask();
        for(Task task:tasks){
            System.out.println(task.toString());
        }
    }

    @Ignore
    public void taskTest(){
     //测试认领任务
        int taskId=11;
        int userId=1;
         String RELE_TYPE_SELF_SELECT="1";
         String RELE_TYPE_BE_CLAIM="1";
         String RELE_TYPE_BE_SELECT="3";
        String RELE_TYPE_BE_OVER="2";
        //System.out.println("------------测试是否被选中---------------");
        if (releMapper.selectIsBeSelect(taskId,userId)==1){
            System.out.println("userId ="+userId+"被选中执行taskId="+taskId+"的任务");
        }else{
            System.out.println("userId ="+userId+"没有被选中执行taskId="+taskId+"的任务");
        }


        //System.out.println("------------测试任务是否被认领---------------");
        if (releMapper.selectIsBeClaim(taskId,RELE_TYPE_BE_CLAIM)==1){
            System.out.println("userId ="+userId+"认领了，taskId="+taskId+"的任务");
        }
        else{
            System.out.println("userId ="+userId+"没有认领了，taskId="+taskId+"的任务");
        }

        if (releMapper.updateReleType(userId, taskId, RELE_TYPE_BE_OVER)==1){
            System.out.println("任务认领成功");
        }


//       System.out.println("------------测试userId="+userId+"的用户自己是否已经认领了taskId="+taskId+"的任务");

//        if(releMapper.selectIsBeSelectBySelf(userId,taskId,RELE_TYPE_SELF_SELECT)==1){
//            System.out.println("userId ="+userId+"认领了，taskId="+taskId+"的任务");
//        }else{
//            System.out.println("userId ="+userId+"没有认领了，taskId="+taskId+"的任务");
//        }



    }
    @Test
    public void dateTest(){
        Date date=new Date();
        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String  formatDate=format1.format(date);
        System.out.println(formatDate);
    }
}
