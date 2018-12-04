//package com.lypee.controller;
//
//import checkcode.patchca.utils.Constants;
//import com.lypee.model.SessionUser;
//import com.lypee.service.IncomeService;
//import com.lypee.service.ItemService;
//import com.lypee.service.MonthlyStatisticsService;
//import com.lypee.service.PayMethodService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpSession;
//import java.util.Map;
//
//public class IncomeController {
//    private IncomeService incomeService ;
//    private ItemService itemService ;
//    private MonthlyStatisticsService monthlyStatisticsService ;
//    private PayMethodService payMethodService ;
//
//    public IncomeService getIncomeService() {
//        return incomeService;
//    }
//@Autowired
//    public void setIncomeService(IncomeService incomeService) {
//        this.incomeService = incomeService;
//    }
//
//    public ItemService getItemService() {
//        return itemService;
//    }
//@Autowired
//    public void setItemService(ItemService itemService) {
//        this.itemService = itemService;
//    }
//
//    public MonthlyStatisticsService getMonthlyStatisticsService() {
//        return monthlyStatisticsService;
//    }
//@Autowired
//    public void setMonthlyStatisticsService(MonthlyStatisticsService monthlyStatisticsService) {
//        this.monthlyStatisticsService = monthlyStatisticsService;
//    }
//
//    public PayMethodService getPayMethodService() {
//        return payMethodService;
//    }
//@Autowired
//    public void setPayMethodService(PayMethodService payMethodService) {
//        this.payMethodService = payMethodService;
//    }
//    @RequestMapping("showIncome")
//    public String showIncomes(Model model , HttpSession session)
//    {
//        Map<String, Object>mao =monthlyStatisticsService(session) ;
//        SessionUser sessionUser = (SessionUser)session.getAttribute(Constants.SESSION_USER_KEY);
//        int userId = sessionUser.getUserId() ;
//        model
//    }
//}
