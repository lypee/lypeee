package com.lypee.controller;

import com.lypee.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FrameController {
    @RequestMapping("showframe")
    public String frame(Model model)
    {
        return "pages/frame";
    }
    @RequestMapping("head")
    public String head(Model model)
    {
        return "pages/head";
    }
    @RequestMapping("menu")
    public String menu(Model model)
    {
        return "pages/menu" ;
    }

}
