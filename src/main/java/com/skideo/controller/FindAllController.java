package com.skideo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindAllController {

    @GetMapping
    public String get(){
        return "If you have token";
    }
}
