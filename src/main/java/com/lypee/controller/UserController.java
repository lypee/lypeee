//package com.lypee.controller;
//
//import checkcode.patchca.color.SingleColorFactory;
//import checkcode.patchca.filter.predefined.CurvesRippleFilterFactory;
//import checkcode.patchca.service.ConfigurableCaptchaService;
//import checkcode.patchca.utils.Constants;
//import checkcode.patchca.utils.encoder.EncoderHelper;
//import com.lypee.exception.BusinessException;
//import com.lypee.model.SessionUser;
//import com.lypee.model.User;
//import com.lypee.service.ItemService;
//import com.lypee.service.ItemService;
//import com.lypee.service.PayMethodService;
//import com.lypee.service.UserService;
//import com.lypee.util.DateTimeUtil;
//import com.lypee.util.EmailUtil;
//import com.lypee.util.PasswordUtil;
//import com.lypee.util.StringTools;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.ui.Model ;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.awt.*;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//
////@Controller
//public class UserController {
//    private Logger logger = LoggerFactory.getLogger(UserController.class);
//    private UserService userService ;
//    private ItemService itemService ;
//    private PayMethodService payMethodService ;
//    private Model model ;
//    /**
//     * 注册页面
//     */
//    @RequestMapping("/showUserRegister")
//    public String showUserRegister(Model model)
//    {
//        return "pages/userRegister";
//    }
//    @RequestMapping("/showUserActive")
//    public String showUserActive(Model model)
//
//    {
//        return "pages/userActive" ;
//    }
//    @RequestMapping("/showUserLogin")
//    public String showUserLogin(Model model)
//    {
//        return "pages/userLogin";
//    }
//    @RequestMapping("/checkCode")
//    public void checkCode(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse,
//                          HttpSession httpSession) throws IOException
//    {
//        ConfigurableCaptchaService configurableCaptchaService = new ConfigurableCaptchaService();
//        configurableCaptchaService.setColorFactory(new SingleColorFactory(new Color(20,60,170)));
//        configurableCaptchaService.setFilterFactory(new CurvesRippleFilterFactory(configurableCaptchaService.getColorFactory()));
//
//        httpServletResponse.setHeader("Pragma","no-cache");
//        httpServletResponse.setHeader("Cache-Control","no-cache");
//        httpServletResponse.setDateHeader("Expires", 0);
//        httpServletResponse.setContentType("image/jpeg");
//        String code= EncoderHelper.getChallangeAndWriteImage(configurableCaptchaService,
//                "png",httpServletResponse.getOutputStream());
//        httpSession.setAttribute(Constants.check_code_key , code);
//    }
//
//    /**
//     * 根据邮箱判断邮箱是否被激活
//     *未注册 ->插入数据
//     * 已注册 未激活 ->激活
//     * 已注册 已激活 登陆
//     * @param session
//     * @param userName
//     * @param email
//     * @param password
//     * @return
//     */
//    @RequestMapping(value = "/register" , produces = "application/json;charset=UTF-8")
//    public @ResponseBody
//    Map<String, Object> register(HttpSession session , String userName ,
//                                 String email , String  password )
//    {
//     Map<String , Object> map = new HashMap<>() ;
//     //0  1 2 3 未注册 已注册未激活 已注册已激活
//        int emailState= userService.isEmailRegister(email);
//        if(emailState == 0 || emailState == 1)
//        {
//            User user = userService.findUserByUserName(userName);
//            if(user != null && !user.getEmail().equals(email))
//            {
//                map.put("info","用户名被占用 请修改");
//                return map ;
//            }
//            String activationCode = EmailUtil.sendEmail(email);
//            User user1  = new User() ;
//            user1.setName(userName);
//            user1.setName(userName);
//            user1.setEmail(email);
//            user1.setPassword(password);
//            user1.setIsActive(Constants.NOT_ACTIVE);
//            user1.setActivationCode(activationCode);
//            user1.setPassword(PasswordUtil.geneMD5WithSalt(user.getPassword()));
//            user1.setRegisterTime(new Date());
//            user1.setLastLoginTime(new Date());
//            user1.setActivationCodeTime(DateTimeUtil.nowTime());
//            if(emailState == 0 )
//            {
//                int userId = userService.register(user) ;
//                initItem(userId) ;
//                initPayMethod(userId) ;
//            }else if(emailState == 1) //更新
//            {
//                int userId= userService.findUserByEmail(email).getId() ;
//                user.setId(userId);
//                userService.update(user) ;
//            }
//            session.setAttribute(Constants.EMAIL ,email);
//            map.put("info","下一步");
//            return map ;
//        }else if(emailState == 2)
//        {
//            map.put("info","邮箱已经被注册 ,请登陆");
//            return map ;
//        }
//        return map ;
//    }
//
//    /**
//     * 激活邮箱
//     */
//    @RequestMapping(value = "/active",produces = "application/json;charset=UTF-8")
//    public @ResponseBody Map<String ,Object> active(User user)
//    {
//            String result = userService.active(user.getEmail() ,user.getActivationCode());
//            Map<String ,Object> map = new HashMap<>();
//            map.put("info",result) ;
//            return map ;
//    }
//
//    /**
//     * 登陆
//     */
//    @RequestMapping(value = "/login" ,produces = "application/json;charset=UTF-8")
//            public @ResponseBody Map <String ,Object> login (HttpSession session, HttpServletResponse response, String account, String password,  String checkCode,String rememberMe)
//    {
//    final String REMEMERBER = "true" ;
//    Map<String ,Object> map = new HashMap<>();
//    try
//    {
//        String sessionCheckCode = String.valueOf(session.getAttribute(Constants.check_code_key));
//        if(StringTools.isEmpty(sessionCheckCode))
//        {
//            map.put("info" ,"验证码已经过期");
//            logger.info("验证码过期 刷新页面");
//            return map ;
//        }
//        if(!StringTools.isEmpty(sessionCheckCode) && !sessionCheckCode.equalsIgnoreCase(checkCode))
//        {
//            map.put("info","验证码错误");
//            logger.info("验证码错误");
//            return map ;
//        }
//        User user = userService.login(account ,password, false) ;
//        SessionUser sessionUser = new SessionUser() ;
//        sessionUser.setUserId(user.getId());
//        sessionUser.setUserName(user.getName());
//        session.setAttribute(Constants.SESSION_USER_KEY,sessionUser);
//        session.setAttribute(Constants.USER_ID,user.getId());
//        //记住登陆状态
//        if(REMEMERBER.equals(rememberMe))
//        {
//          //pass
//        }
//
//    }catch (BusinessException e)
//    {
//        if(map.get("info") == null)
//        {
//            map.put("info",e.getMessage());
//            logger.info("登陆失败"+e.getMessage());
//            return map ;
//        }
//    }
//    catch (Exception e)
//    {
//        if(map.get("info") == null)
//        {
//            map.put("info","登陆失败");
//            logger.info("登陆失败");
//        }
//        return map ;
//    }
//    map.put("info","登陆成功");
//return map ;
//    }
//    /**
//     * 注销
//     * @param session
//     * @return
//     */
//    /**
//     * 注销
//     * @param session
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/logout.action", produces = "application/json;charset=UTF-8")
//    public @ResponseBody Map<String ,Object> logout(HttpSession session, HttpServletResponse response) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        //清空session
//        session.setAttribute(Constants.SESSION_USER_KEY, null);
//        //清空cookie
//        Cookie cookie = new Cookie(Constants.COOKIE_USER_INFO, null);
//        cookie.setPath("/");
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//
//        map.put("info", "注销成功");
//        return map;
//    }
//    private void initItem(int userId) {
//        itemService.addItem(userId, " ", "空项目", "in");
//        itemService.addItem(userId, "工资", " ", "in");
//        itemService.addItem(userId, "其他", " ", "in");
//        itemService.addItem(userId, " ", "空项目", "ex");
//        itemService.addItem(userId, "餐饮", " ", "ex");
//        itemService.addItem(userId, "服饰", " ", "ex");
//        itemService.addItem(userId, "医疗", " ", "ex");
//        itemService.addItem(userId, "其他", " ", "ex");
//    }
//    private void initPayMethod(int userId) {
//        payMethodService.addPayMethod(userId, "余额宝", -1, "in", "");
//        payMethodService.addPayMethod(userId, "现金", -1, "in", "");
//        payMethodService.addPayMethod(userId, "微信", -1, "in", "");
//        payMethodService.addPayMethod(userId, "银行卡", -1, "in", "");
//
//        payMethodService.addPayMethod(userId, "余额宝", 1, "ex", "");
//        payMethodService.addPayMethod(userId, "现金", 1, "ex", "");
//        payMethodService.addPayMethod(userId, "微信", 1, "ex", "");
//        payMethodService.addPayMethod(userId, "银行卡", 1, "ex", "");
//        payMethodService.addPayMethod(userId, "花呗", 0, "ex", "");
//        payMethodService.addPayMethod(userId, "信用卡", 0, "ex", "");
//    }
//}
