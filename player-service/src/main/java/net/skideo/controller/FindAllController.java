package net.skideo.controller;


import net.skideo.model.Comment;
import net.skideo.model.User;
import net.skideo.model.Video;
import net.skideo.repository.CommentRepository;
import net.skideo.repository.VideoRepository;
import net.skideo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FindAllController {

    private final UserService userService;

    private final CommentRepository repository;

    @GetMapping("/all")
    public List<Comment> getAll() {
        return repository.findAll();
    }


}


