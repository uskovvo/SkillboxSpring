package com.example.mybookshopapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpPageController {

    @GetMapping("/faq")
    public String getDocuments(){
        return "faq";
    }
}
