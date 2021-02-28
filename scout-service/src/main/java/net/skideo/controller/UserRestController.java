package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.SearchDto;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @GetMapping("/search")
    public Page<SearchDto> search(@RequestParam(required = false) String country, @RequestParam(required = false) RoleFootball roleFootball, @RequestParam(required = false) boolean agent,
                                  @RequestParam(required = false) RolePeople rolePeople, @RequestParam(required = false) LeadingLeg leadingLeg, @RequestParam(required = false) LocalDate dateOfBirth,
                                  @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        return userService.search(country, roleFootball, agent, rolePeople, leadingLeg, dateOfBirth, page,size);
    }

}
