package com.football.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for login page.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */

@Controller
public class LoginController {
    @GetMapping("/login")
    public String authenticationPage() {
        return "login";
    }
}
