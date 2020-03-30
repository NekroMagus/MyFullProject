package com.github.skideo.controller;

import com.github.skideo.dto.SearchUserDto;
import com.github.skideo.exception.UserNotFoundException;
import com.github.skideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class SearchUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/search")
    public ResponseEntity<?> getSearchResult(@RequestBody SearchUserDto search) {
        if (search.getAge() != 0 && !search.getCountry().equals("") && search.getRoleFootball() != null) {
            return ResponseEntity.ok(userService.findByDateOfBirthBetweenAndRoleFootballAndCountry(getDateBirthStart(search.getAge()),
                    getDateBirthEnd(search.getAge()), search.getRoleFootball(), search.getCountry()));
        } else if (search.getAge() != 0 && search.getRoleFootball() != null) {
            return ResponseEntity.ok(userService.findByDateOfBirthBetweenAndRoleFootball(getDateBirthStart(search.getAge()),
                    getDateBirthEnd(search.getAge()), search.getRoleFootball()));
        } else if (search.getAge() != 0 && !search.getCountry().equals("")) {
            return ResponseEntity.ok(userService.findByDateOfBirthBetweenAndCountry(getDateBirthStart(search.getAge()),
                    getDateBirthEnd(search.getAge()), search.getCountry()));
        } else if (search.getRoleFootball() != null && !search.getCountry().equals("")) {
            return ResponseEntity.ok(userService.findByRoleFootballAndCountry(search.getRoleFootball(),
                    search.getCountry()));
        } else if (search.getAge() != 0) {
            return ResponseEntity.ok(userService.findByDateOfBirthBetween(getDateBirthStart(search.getAge()),
                    getDateBirthEnd(search.getAge())));
        } else if (!search.getCountry().equals("")) {
            return ResponseEntity.ok(userService.findByCountry(search.getCountry()));
        } else if (search.getRoleFootball() != null) {
            return ResponseEntity.ok(userService.findByRoleFootball(search.getRoleFootball()));
        } else {
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/id{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(new SearchUserDto(userService.findById(id)));
    }

    private LocalDate getDateBirthStart(int age) {
        LocalDate date = LocalDate.of(LocalDate.now().getYear() - age - 1,
                LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        date = date.plusDays(1);
        return date;
    }

    private LocalDate getDateBirthEnd(int age) {
        return LocalDate.of(LocalDate.now().getYear() - age, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
    }
}