package com.example.mybookshopapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInPageController {

    @GetMapping("/signin")
    public String getSignIn(){
        return "signin";
    }
}
