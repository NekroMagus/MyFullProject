package com.football.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for index page.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String helloPage() {
        return "index";
    }
}
