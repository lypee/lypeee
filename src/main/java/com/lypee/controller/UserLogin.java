package com.lypee.controller;


import com.lypee.dao.UserDao;
import com.lypee.model.User;
import com.lypee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class UserLogin {
    @Autowired
    UserService userService ;
    Model model ;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-HH-dd HH-MM-ss") ;

    /**
     * 对应用户名 密码 真实姓名

     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(User user ,Model model)
    {
        user.setRemark("1");
        user.setRegisterTime(new Date());
        System.out.println(user.getId()+ " " + user.getName()+ user.getEmail()
        +" " + user.getPassword() + " " + user.getAge() + " " + user.getSex() +" "
        + user.getRemark() + " " + user.getRegisterTime());
        userService.insert(user);

        return "loginForm" ;
    }

    /**
     * 登陆界面 判断用户是否在数据库中 .如果不在 返回<1 .
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "loginFrame" ,method = RequestMethod.POST)
    public String loginTest(@ModelAttribute User user , Model model)
    {
        List<User> userList = userService.findUserByUserName(user.getName());
        if(userList == null) return "register" ;

//        if(user.getName() == null
          else return "hello" ;
//        return "/pages/loginForm";
    }

    @RequestMapping("getAllUser")
    public String getAllUser(HttpServletRequest request ,Model model)
    {
        List<User> user = userService.findAll() ;
        model.addAttribute("userList",user);
        request.setAttribute("userList" ,user);
        return "allUser" ;
    }


    @RequestMapping("/getUser")
    public String getUser(int id,HttpServletRequest request,Model model){
        request.setAttribute("user", userService.findById(id));
        model.addAttribute("user", userService.findById(id));
        return "editUser";
    }
    @RequestMapping("toEditUser")
    public String editUser(User user,HttpServletRequest request ,Model model)
    {
       return "editUser" ;
    }
    @RequestMapping("editUser")
    public String editUser(User user ,Model model)
    {
        //根据id修改
        if(userService.updateByPrimaryKeySelective(user) > 0) return "redirect:getAllUser" ;
        return  "error";
    }
    @RequestMapping("toAddUser")
    public String toaddUser(){
        return "addUser";
    }
    @RequestMapping("addUser")
            public String addUser(User user , Model model)
    {
        user.setLastLoginTime(new Date());
        userService.insert(user);
        return "redirect:getAllUser" ;
    }
    //update
    @RequestMapping("updateUser")
    public String updateUser(User user ,HttpServletRequest request , Model model)
    {
        if(userService.update(user) > 0)
        {
            user = userService.findById(user.getId());
            request.setAttribute("user",user);
            model.addAttribute("user",user);
            return "redirect:getAllUser";
        }else
        {
            return "error" ;
        }
    }
    /**
     * 删除
     */
    @RequestMapping("delUser")
    public void delUser(int id ,HttpServletRequest request ,HttpServletResponse response)
    { String result = "{\"result\":\"error\"}";
if(userService.delete(id) > 0 ){
        result = "{\"result\":\"success\"}";
    }
        response.setContentType("application/json");
try {
        PrintWriter out = response.getWriter();
        out.write(result);
    } catch (
    IOException e) {
        e.printStackTrace();
    }
}
}
