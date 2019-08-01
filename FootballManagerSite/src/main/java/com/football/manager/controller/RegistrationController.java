package com.football.manager.controller;

import com.football.manager.domain.Photo;
import com.football.manager.domain.role.RoleInFootball;
import com.football.manager.domain.User;
import com.football.manager.service.PhotoService;
import com.football.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @PostMapping("/addUser")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("surname") String surname,
                          @RequestParam("email") String email,
                          @RequestParam("telephoneNumber") String telephoneNumber,
                          @RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfBirth,
                          @RequestParam("address") String address,
                          @RequestParam("roleInFootball") RoleInFootball roleInFootball,
                          @RequestParam("login") String login,
                          @RequestParam("password") String password,
                          @RequestParam("socialNetwork") String socialNetwork,
                          @RequestParam("image") MultipartFile file
    ) throws IOException {

        User user = new User(login, password, email, name, surname, telephoneNumber,
                address, roleInFootball, dateOfBirth, socialNetwork);

        if (file.getContentType().startsWith("image")) {
            Photo photo = new Photo();
            photo.setName(file.getOriginalFilename());
            photo.setContent(file.getBytes());
            photo.setType(file.getContentType());
            photo.setDescription("");
            photo.setUserPhoto(user);
            user.getPhotoList().add(photo);
            photoService.saveFile(photo);
        } else {
            throw new IOException("Неверный формат файла");
        }
        userService.addUser(user);
        return "redirect:/";
    }
}
