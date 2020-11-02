package net.skideo.controller;

import net.skideo.dto.AuthDto;
import net.skideo.dto.TokenDto;
import net.skideo.dto.UserRegDto;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.model.User;
import net.skideo.model.jwt.JwtRequest;
import net.skideo.model.jwt.JwtResponse;
import net.skideo.security.jwt.JwtTokenUtil;
import net.skideo.service.auth.AuthService;
import net.skideo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public TokenDto authenticate(@RequestBody AuthDto authDto) throws Exception {
        final User USER  = userService.findByLogin(authDto.getLogin());
        if(!authService.isCorrectPassword(authDto,USER)) {
            throw new WrongLoginOrPasswordException("Wrong login or password");
        }
        return new TokenDto(jwtTokenUtil.generateToken(authDto.getLogin()));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserRegDto userRegDto) throws Exception {

    }


}
