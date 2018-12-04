package com.lypee.dao;

import com.lypee.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public interface UserDao {
    int insert(User record);
    List<User> findAll();
    List<User> findUserByUserName(String name);

    List<User> findUserByEmail(String email);

    int updateByPrimaryKeySelective(User user) ;

    int isEmailRegister(String email) ;

    int update(User user) ;

    int delete(int id) ;//by id
    User findById(int id) ;
    int getActiveNum(User user) ;
    int logOutbyId(int id) ;
}
