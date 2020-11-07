package net.skideo.controller;


import lombok.RequiredArgsConstructor;
import net.skideo.model.User;
import net.skideo.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FindAllController {

    private final UserService userService;

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }


}


