package com.cqz.test;

import com.cqz.PublishingApplication;
import com.cqz.dao.TaskMapper;
import com.cqz.model.Task;
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

    @Test
    public void selectTest(){
        List<Task> tasks=taskMapper.selectAllTask();
        for(Task task:tasks){
            System.out.println(task.toString());
        }
    }

    @Test
    public void dateTest(){
        Date date=new Date();


    }
}
