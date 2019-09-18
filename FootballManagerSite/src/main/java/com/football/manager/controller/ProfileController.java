package com.football.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for profile page.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("/edit")
    public String getProfileEdit(){
        return "edit";
    }

    @GetMapping
    public String getProfile(){
        return "profile";
    }
}
