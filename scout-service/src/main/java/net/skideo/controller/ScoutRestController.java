package net.skideo.controller;

import net.skideo.dao.ScoutDao;
import net.skideo.dto.ProfileDto;
import net.skideo.dto.SearchDto;
import net.skideo.dto.UpdateProfileDto;
import net.skideo.model.Scout;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.service.scout.ScoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/scout")
public class ScoutRestController {

     @Autowired
     private ScoutService scoutService;

     @Autowired
     private ScoutDao dao;

     @GetMapping("/profile")
     public ProfileDto getProfile() {
         return scoutService.getProfile();
     }

     @PutMapping
     public void updateProfile(@RequestBody UpdateProfileDto dto) {
          scoutService.updateProfile(dto);
     }

     @GetMapping("/search")
     public List<SearchDto> search(@RequestParam String country, @RequestParam RoleFootball roleFootball, @RequestParam boolean agent, @RequestParam RolePeople rolePeople, @RequestParam LeadingLeg leadingLeg, @RequestParam LocalDate dateOfBirth) {
          return scoutService.search(country,roleFootball,agent,rolePeople,leadingLeg,dateOfBirth);
     }

     @PostMapping("/user/favorite/{id}")
     public void addUserToFavorite(@PathVariable("id") long id) {
          scoutService.addUserToFavorite(id);
     }

     @GetMapping("/all")
     public List<Scout> all() {
          return dao.findAll();
     }

}
