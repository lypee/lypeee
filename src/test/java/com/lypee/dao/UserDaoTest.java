package com.lypee.dao;

import com.lypee.BaseTest;
import com.lypee.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest extends BaseTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserDao userDao ;
    @Test
    public void testFindByName() throws Exception
    {
       String name = "ly" ;
       List<User> userList   = userDao.findUserByUserName(name) ;
        System.out.println("\nuser : "  +
               userList.get(0).getId());
    }
    @Test
    public void testInsert()throws Exception
    {
        User user = new User() ;
        user.setId(5);
        user.setName("lypee");
        user.setEmail("237454712@qq.com");
        user.setPassword("123456");
        user.setAge(20);
        user.setSex("男");
        user.setRegisterTime(new Date());
        System.out.println(userDao.insert(user));
    }
}
