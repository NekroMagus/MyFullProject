package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.ProfileDto;
import net.skideo.dto.SearchDto;
import net.skideo.dto.UpdateProfileDto;
import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.model.Scout;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.service.scout.ScoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/scout")
@RequiredArgsConstructor
public class ScoutRestController {

    private final ScoutService scoutService;

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfile() {
        final ScoutProfileProjection CURRENT_SCOUT = scoutService.getProfileByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.status(HttpStatus.OK).body(scoutService.getProfile(CURRENT_SCOUT));
    }

    @PutMapping
    public void updateProfile(@Valid @RequestBody UpdateProfileDto dto) {
        scoutService.updateProfile(dto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchDto>> search(@RequestParam String country, @RequestParam RoleFootball roleFootball, @RequestParam boolean agent,
                                                  @RequestParam RolePeople rolePeople, @RequestParam LeadingLeg leadingLeg, @RequestParam LocalDate dateOfBirth,
                                                  @RequestParam int page,@RequestParam int size) {
        return ResponseEntity.status(HttpStatus.OK).body(scoutService.search(country, roleFootball, agent,
                                                                             rolePeople, leadingLeg, dateOfBirth,
                                                                             page,size));
    }

    @PostMapping("/user/favorite")
    public void addUserToFavorite(@RequestParam long id) {
        final Scout CURRENT_USER = scoutService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        scoutService.addUserToFavorite(id,CURRENT_USER);
    }

}
