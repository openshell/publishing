package com.cqz.test;

import com.cqz.PublishingApplication;
import com.cqz.dao.UserMapper;
import com.cqz.model.Task;
import com.cqz.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author openshell
 * @date 2019/4/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PublishingApplication.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void userTest(){
       User user= userMapper.selectByUserName("openshell");
       System.out.println(user);
    }
}
