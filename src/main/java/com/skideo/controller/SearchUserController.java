package com.skideo.controller;

import com.skideo.dto.SearchUserDto;
import com.skideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<?> getSearchResult(@RequestBody SearchUserDto search){
        return null;
    }

    @GetMapping("/id{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id){
        return ResponseEntity.ok(new SearchUserDto(userService.findById(id)));
    }
}