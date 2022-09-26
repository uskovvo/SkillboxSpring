package org.example.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/404")
    public String notFoundError() {
        return "errors/404";
    }

    @GetMapping("/500")
    public String badRequest() {
        return "errors/500";
    }

    @GetMapping("/405")
    public String nullFile() {
        return "errors/405";
    }
}
