package net.skideo.controller;

import net.skideo.dto.GetRatingDto;
import net.skideo.dto.RatingDto;
import net.skideo.dto.UserDto;
import net.skideo.model.enums.RoleFootball;
import net.skideo.security.jwt.JwtTokenUtil;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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