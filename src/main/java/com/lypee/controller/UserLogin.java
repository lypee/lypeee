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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class UserLogin {
    @Autowired
    UserService userService;
    Model model;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-HH-dd HH-MM-ss");

    /**
     * 对应用户名 密码 真实姓名
     *
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(User user, Model model) {
        user.setRemark("1");
        user.setRegisterTime(new Date());
        System.out.println(user.getId() + " " + user.getName() + user.getEmail()
                + " " + user.getPassword() + " " + user.getAge() + " " + user.getSex() + " "
                + user.getRemark() + " " + user.getRegisterTime());
        userService.insert(user);

        return "loginForm";
    }

    /**
     * 登陆界面 判断用户是否在数据库中 .如果不在 返回<1 .
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "loginFrame", method = RequestMethod.POST)
    public String loginTest(@ModelAttribute User user, Model model ,HttpSession session) {
        //获取匹配的用户列表
        List<User> userList = userService.findUserByUserName(user.getName());
        if (userList.isEmpty() || userList.size() == 0) return "register";

        //获取第一个用户  //更新用户的活跃时间
        userList.get(0).setLastLoginTime(new Date());
        userList.get(0).setIsActive(1);
        //用户活跃次数加一
        userList.get(0).setActive_number(userList.get(0).getActive_number() + 1);

        //更新
        userService.updateByPrimaryKeySelective(userList.get(0));

        session.setAttribute("user",user);
//        if(user.getName() == null
        return "hello";
//        return "/pages/loginForm";
    }

    /**
     * 显示所有学员信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("getAllUser")
    public String getAllUser(HttpServletRequest request, Model model) {
        List<User> user = userService.findAll();
        model.addAttribute("userList", user);
        request.setAttribute("userList", user);
        return "allUser";
    }

    /**
     * 得到user
     * @param id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/getUser")
    public String getUser(int id, HttpServletRequest request, Model model) {
        request.setAttribute("user", userService.findById(id));
        model.addAttribute("user", userService.findById(id));
        return "editUser";
    }

    @RequestMapping("toEditUser")
    public String editUser(User user, HttpServletRequest request, Model model) {
        return "editUser";
    }

    /**
     * 根据id修改学员信息
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("editUser")
    public String editUser(User user, Model model) {
        //根据id修改
        if (userService.updateByPrimaryKeySelective(user) > 0) return "redirect:getAllUser";
        return "error";
    }

    @RequestMapping("toAddUser")
    public String toaddUser() {
        return "addUser";
    }

    /**
     * 添加学员信息
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("addUser")
    public String addUser(User user, Model model) {
        user.setLastLoginTime(new Date());
        userService.insert(user);
        return "redirect:getAllUser";
    }

    //update

    /**
     * 更新学员信息
     * @param user
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("updateUser")
    public String updateUser(User user, HttpServletRequest request, Model model) {
        if (userService.update(user) > 0) {
            user = userService.findById(user.getId());
            request.setAttribute("user", user);
            model.addAttribute("user", user);
            return "redirect:getAllUser";
        } else {
            return "error";
        }
    }

    /**
     * 删除健身房学员
     */
    @RequestMapping("delUser")
    public void delUser(int id, HttpServletRequest request, HttpServletResponse response) {
        String result = "{\"result\":\"error\"}";
        if (userService.delete(id) > 0) {
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

    /**
     * 用户离开健身房
     * 本质是 is_active 置为0  ;回退或登陆界面
     */
    @RequestMapping("logOut")
    public String logOut(int id,Model model) {
        if(userService.logOutbyId(id) > 0) return "loginForm" ;
        else  return  "error" ;
    }
    @RequestMapping("toEntryGym")
    public String toEntryGym(User user , HttpSession session)
    {
        session.getAttribute("user") ;
        user.setIsActive(1);
        user.setLastLoginTime(new Date());
       if(userService.updateByPrimaryKeySelective(user) > 0)
        return "redirect:getAllUser" ;
       return "error" ;
    }
    @RequestMapping("LeaveGym")
    public String LeaveGym(User user , HttpSession session)
    {
        session.getAttribute("user");
        user.setIsActive(0);
        user.setLeaveTime(new Date());
        System.out.println(user.getLeaveTime());
        if(userService.updateByPrimaryKeySelective(user) > 0)
        {
            return "redirect:getAllUser" ;
        }
        return "error" ;
    }
    @RequestMapping("DelayTime")
    public String DelayTime(User user ,HttpSession session)
    {
        Calendar calendar = Calendar.getInstance() ;//当前日期
        calendar.add(Calendar.DATE ,180);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
        String expirationTime= simpleDateFormat.format(calendar) ;
        session.getAttribute("user");
        user.setExpirationTime(expirationTime);
        userService.updateByPrimaryKeySelective(user) ;
        return "redirect:getAllUser" ;
    }
}