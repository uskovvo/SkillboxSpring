package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);
    @GetMapping (value = "/login")
    public ModelAndView login(){
        logger.info("GET /login returns login_page.html");
        return new ModelAndView("login_page");
    }
}
