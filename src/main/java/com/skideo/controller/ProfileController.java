package com.skideo.controller;

import com.skideo.config.JwtTokenUtil;
import com.skideo.dto.UserDto;
import com.skideo.model.role.RoleFootball;
import com.skideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
public class ProfileController {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public ProfileController(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        final String jwtToken = request.getHeader("Authorization").substring(7);
        final String login = jwtTokenUtil.getUsernameFromToken(jwtToken);
        return ResponseEntity.ok(new UserDto(userService.findByLogin(login)));
    }

    @GetMapping("/roleFootball")
    public ResponseEntity<?> getRoleFootball() {
        return ResponseEntity.ok(Arrays.toString(RoleFootball.values()));
    }
}