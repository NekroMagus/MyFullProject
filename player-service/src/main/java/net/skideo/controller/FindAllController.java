package net.skideo.controller;


import net.skideo.model.Player;
import net.skideo.service.user.UserService;
import lombok.RequiredArgsConstructor;
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
    public List<Player> getAll() {
        return userService.findAll();
    }


}


