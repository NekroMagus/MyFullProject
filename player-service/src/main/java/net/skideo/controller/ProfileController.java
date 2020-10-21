package net.skideo.controller;

import data.service.dto.UserDto;
import data.service.model.role.RoleFootball;
import net.skideo.security.jwt.JwtTokenUtil;
import net.skideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity getProfile(HttpServletRequest request) {
        final String jwtToken = request.getHeader("Authorization").substring(7);
        final String login = jwtTokenUtil.getUsernameFromToken(jwtToken);
        return ResponseEntity.ok(new UserDto(userService.findByLogin(login)));
    }

    @PutMapping("/profile")
    public ResponseEntity editProfile(@RequestBody UserDto newUser) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = userService.editUser(newUser, login);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/roleFootball")
    public ResponseEntity getRoleFootball() {
        return ResponseEntity.ok(Arrays.toString(RoleFootball.values()));
    }


}