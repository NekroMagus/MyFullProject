package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.SearchUserDto;
import net.skideo.exception.UserNotFoundException;
import net.skideo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class SearchUserController {

    private final UserService userService;

    @PostMapping("/search")
    public ResponseEntity<?> getSearchResult(@RequestBody SearchUserDto search) {
        if (search.getAge() != 0 && !search.getCountry().equals("") && search.getRoleFootball() != null) {
            return ResponseEntity.ok(userService.findByBirthDateBetweenAndRoleFootballAndCountry(getDateBirthStart(search.getAge()),
                    getDateBirthEnd(search.getAge()), search.getRoleFootball(), search.getCountry()));
        } else if (search.getAge() != 0 && search.getRoleFootball() != null) {
            return ResponseEntity.ok(userService.findByBirthDateBetweenAndRoleFootball(getDateBirthStart(search.getAge()),
                    getDateBirthEnd(search.getAge()), search.getRoleFootball()));
        } else if (search.getAge() != 0 && !search.getCountry().equals("")) {
            return ResponseEntity.ok(userService.findByBirthDateBetweenAndCountry(getDateBirthStart(search.getAge()),
                    getDateBirthEnd(search.getAge()), search.getCountry()));
        } else if (search.getRoleFootball() != null && !search.getCountry().equals("")) {
            return ResponseEntity.ok(userService.findByRoleFootballAndCountry(search.getRoleFootball(),
                    search.getCountry()));
        } else if (search.getAge() != 0) {
            return ResponseEntity.ok(userService.findByBirthDateBetween(getDateBirthStart(search.getAge()),
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