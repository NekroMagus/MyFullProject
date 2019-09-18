package com.football.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for login page.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String authenticationPage() {
        return "login";
    }
}
