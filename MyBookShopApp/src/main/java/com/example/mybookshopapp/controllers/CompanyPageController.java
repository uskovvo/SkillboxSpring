package com.example.mybookshopapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyPageController {

    @GetMapping("/about")
    public String getDocuments(){
        return "about";
    }
}
