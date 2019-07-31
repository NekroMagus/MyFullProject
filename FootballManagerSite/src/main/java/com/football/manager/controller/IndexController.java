package com.football.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String helloPage(){
        return "index";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @GetMapping("/authentication")
    public String authentificationPage(){
        return "authentication";
    }
}
