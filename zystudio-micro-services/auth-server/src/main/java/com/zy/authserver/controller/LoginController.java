package com.zy.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author by zy.
 */
@Controller
public class LoginController {


    @RequestMapping("/login")
    public String login(){
        return "login";
        //return new ModelAndView("login");
    }
}
