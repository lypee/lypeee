package com.lypee.service.impl;

import checkcode.patchca.utils.Constants;
import com.lypee.exception.BusinessException;
import com.lypee.dao.ItemDao;
import com.lypee.dao.UserDao;
import com.lypee.model.User;
import com.lypee.service.UserService;
import com.lypee.util.DateTimeUtil;
import com.lypee.util.PasswordUtil;
import com.lypee.util.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDao userDao ;
    @Autowired
    public UserDao getUserDao() {
        return userDao;
    }


    /**
     * 注册
     */
    @Override
    public int register(User user)
    {
        int recordNum = userDao.insert(user) ;
        int userId = 0  ;
        if(recordNum == 1) ;//修改成功
        {
            userId = user.getId() ;
        }
        return userId ;
    }


    @Override
    public List<User> findUserByEmail(String email) {
        if(userDao == null) userDao = getUserDao() ;
        List<User> userList = userDao.findUserByEmail(email);
        return userList;
    }

    @Override
    public List<User> findUserByUserName(String userName) {
            if(userDao == null){
                userDao = getUserDao();
            }
            List<User> userList = userDao.findUserByUserName(userName);
            if(userList == null) return null;
            else return userList ;
    }

    @Override
    public int isEmailRegister(String email) {
        return userDao.isEmailRegister(email);//
    }

    @Override
    public int update(User user)
        {

            return userDao.update(user);
        }



    @Override
    public List<User> findAll() {
       List<User> findAllList = userDao.findAll();
       return findAllList ;
    }

    /**
     * 注册方法
     * 本质就是一个插入过程
     * @param user
     * @return
     */
    @Override
    public int insert(User user)
    {
       //前端传的参数有name email age password sex
        //需要自己添加id
        user.setRegisterTime(new Date());
        return userDao.insert(user);
    }
    public int delete(int id)
    {
       return  userDao.delete(id);

    }
    public User findById(int id)
    {
        return userDao.findById(id) ;
    }

    /**
     * 根据id修改
     * @param user
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userDao.updateByPrimaryKeySelective(user);
    }

}
