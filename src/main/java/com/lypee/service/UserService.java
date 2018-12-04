package com.lypee.service;

import com.lypee.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Component
public interface UserService {
        public int register(User user) ;
        List<User> findUserByEmail(String email);
        List<User> findUserByUserName(String name) ;
        int isEmailRegister(String email) ;
        int insert(User user) ;
        int update(User user) ;
        List<User> findAll();
        int delete(int id) ;
        User findById(int id);
        int updateByPrimaryKeySelective(User user) ;
}
