package com.football.manager.controller;

import com.football.manager.model.User;
import com.football.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller for search page.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getSearch() {
        return "search";
    }

    @GetMapping("/result")
    public String getResultSearch(@RequestParam String found, Model model) {
        List<User> userList = userService.findUsersByName(found);
        model.addAttribute("userList",userList);
        return "search";
    }
}
